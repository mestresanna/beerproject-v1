package be.kdg.programming3.prog3_spring.repository;

import be.kdg.programming3.prog3_spring.Domain.Beer;
import be.kdg.programming3.prog3_spring.Domain.Customer;
import be.kdg.programming3.prog3_spring.Domain.Order;
import be.kdg.programming3.prog3_spring.exceptions.DataBaseException;
import be.kdg.programming3.prog3_spring.service.BeerService;
import be.kdg.programming3.prog3_spring.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Profile("jdbc")
public class OrderJBDCRepository implements OrderRepository {
    private final CustomerService customerService;
    private final BeerService beerService;
    private Logger logger = LoggerFactory.getLogger(OrderJBDCRepository.class);

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert insertOrder;

    public OrderJBDCRepository(JdbcTemplate jdbcTemplate, CustomerService customerService, BeerService beerService) {
        this.jdbcTemplate = jdbcTemplate;
        insertOrder = new SimpleJdbcInsert(jdbcTemplate).withTableName("orders").usingGeneratedKeyColumns("idorder");
        this.customerService = customerService;
        this.beerService = beerService;
    }

    @Override
    public Order save(Order order) {
        PreparedStatementCreatorFactory pscf = new
                PreparedStatementCreatorFactory("INSERT INTO ORDERS(DATE, COMMENTS, CUSTOMERID, TOTAL) VALUES (?, ?, ?, ?)",
                Types.DATE, Types.VARCHAR, Types.INTEGER, Types.INTEGER);
        pscf.setReturnGeneratedKeys(true);

        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(List.of(LocalDate.now(), order.getComments(), order.getCustomer().getIdCustomer(), order.getTotal()));

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(psc, keyHolder);
        order.setIdOrder(keyHolder.getKey().intValue());

        setOrderToBeer(order);
        setOrderToCustomer(order);
        logger.debug("Creating new order: {}, with id: {}", order, order.getIdOrder());
        return order;
    }

    @Override
    public void setOrderToCustomer(Order order){
        logger.debug("Setting order to customer: {}", order);
        Customer customer = order.getCustomer();
        customer.setOrders(order);
        saveCustomerToBeer(customer);
    }

    @Override
    public void setOrderToBeer(Order order){
        HashMap<Beer, Integer> beers = order.getBeers();
        if (beers!=null && !beers.isEmpty()) {
            for (Map.Entry<Beer, Integer> entry : beers.entrySet()) {
                Beer key = entry.getKey();
                key.setOrders(order);
                logger.debug("Saving " + key + " to orders");
                PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory("INSERT INTO BEER_ORDER(BEERID, ORDERID, QUANTITY) VALUES (?,?,?)",
                        Types.INTEGER, Types.INTEGER, Types.INTEGER);
                PreparedStatementCreator psc = pscf.newPreparedStatementCreator(List.of(key.getIdBeer(), order.getIdOrder(), entry.getValue()));
                jdbcTemplate.update(psc);
                saveOrderToBeer(key, order);
            }
        }
    }


    public void saveOrderToBeer(Beer beer, Order order){
        jdbcTemplate.query("SELECT * FROM BEER_ORDER WHERE ORDERID = ? AND BEERID = ?",
                (ResultSet beerRs, int beerRowNum) -> {
                    int quantity = beer.getStock() - beerRs.getInt("QUANTITY");
                    beer.setOrders(order);
                    beer.setStock(quantity);
                    beerService.updateBeer(beer);
                    return null;
                },
                order.getIdOrder(),
                beer.getIdBeer()
        );
    }


    public void saveCustomerToBeer(Customer customer){
      /*  jdbcTemplate.query("SELECT IDORDER FROM ORDERS WHERE CUSTOMERID = ?",
                (ResultSet customerRS, int beerRowNum) -> {
                    int idOrder = customerRS.getInt("IDORDER");
                    customer.setOrders(idOrder);
                    customerService.updateCustomer(customer);
                    return null;
                },
                customer.getIdCustomer()
        );*/
    }

    @Override
    public Order findById(int orderId) {
        logger.debug("Finding order with id: {}", orderId);
        return jdbcTemplate.queryForObject("SELECT * FROM ORDERS WHERE IDORDER = ?", this::mapRow, orderId);
    }

    @Override
    public List<Order> readAllOrders() {
        logger.debug("Reading all orders");
        List<Order> orders = jdbcTemplate.query("SELECT * FROM ORDERS",
                this::mapRow);

        return orders;
    }

    private Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        String imageUrl = "/images/shopping-cart.png";
        int orderId = rs.getInt("idorder");

        HashMap<Beer, Integer> beers = new HashMap<>();
        jdbcTemplate.query("SELECT * FROM BEER_ORDER WHERE ORDERID = ?",
                (ResultSet beerRs, int beerRowNum) -> {
                    Beer beer = beerService.getBeerById(beerRs.getInt("BEERID"));
                    int quantity = beerRs.getInt("QUANTITY");
                    beers.put(beer, quantity);
                    return null;
                },
                orderId
        );

        return new Order(orderId, rs.getString("comments"), rs.getDate("date").toLocalDate(),
                customerService.getCustomer(rs.getInt("customerid")), beers, imageUrl);
    }



}
