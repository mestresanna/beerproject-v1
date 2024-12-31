package be.kdg.programming3.prog3_spring.repository;

import be.kdg.programming3.prog3_spring.Domain.Beer;
import be.kdg.programming3.prog3_spring.Domain.Customer;
import be.kdg.programming3.prog3_spring.Domain.Order;
import be.kdg.programming3.prog3_spring.service.BeerService;
import be.kdg.programming3.prog3_spring.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

        loadBeerOrder(order);
        loadCustomer(order);
        logger.debug("Creating new order: {}, with id: {}", order, order.getIdOrder());
        return order;
    }

    @Override
    public void loadCustomer(Order order){
        logger.debug("Setting order to customer: {}", order);
        Customer customer = order.getCustomer();
        List<Order> orders = jdbcTemplate.query("SELECT * FROM ORDERS WHERE CUSTOMERID = ? ", this::mapRow, customer.getIdCustomer());
        customer.setOrders(orders);
        logger.info("Orders in customer with id {} , : {}", customer.getIdCustomer(), customer.getOrders());

    }

    @Override
    public void loadBeerOrder(Order order){
        HashMap<Beer, Integer> beers = order.getBeers();
        if (beers!=null && !beers.isEmpty()) {
            for (Map.Entry<Beer, Integer> entry : beers.entrySet()) {
                Beer key = entry.getKey();
                logger.debug("Saving " + key + " to beer_order table");
                PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory("INSERT INTO BEER_ORDER(BEERID, ORDERID, QUANTITY) VALUES (?,?,?)",
                        Types.INTEGER, Types.INTEGER, Types.INTEGER);
                PreparedStatementCreator psc = pscf.newPreparedStatementCreator(List.of(key.getIdBeer(), order.getIdOrder(), entry.getValue()));
                jdbcTemplate.update(psc);
                loadBeer(key, order);
                logger.info("Orders in beer with id {} , : {}", key.getIdBeer(), key.getOrders());
            }
        }
    }

    @Override
    public void loadBeer(Beer beer, Order order){
        jdbcTemplate.query("SELECT * FROM BEER_ORDER WHERE ORDERID = ? AND BEERID = ?",
                (ResultSet beerRs, int beerRowNum) -> {
                    int quantity = beer.getStock() - beerRs.getInt("QUANTITY");
                    beer.setStock(quantity);
                    findByBeer(beer);
                    beerService.updateBeer(beer);
                    return null;
                },
                order.getIdOrder(),
                beer.getIdBeer()
        );
    }

    @Override
    public List<Order> findByBeer(Beer beer){
        List<Order> orders = new ArrayList<>();
        jdbcTemplate.query("SELECT ORDERID FROM BEER_ORDER WHERE BEERID = ?",
                (ResultSet beerRs, int beerRowNum) -> {
                    Order order = findById(beerRs.getInt("orderid"));
                    orders.add(order);
                    return null;
                },
                beer.getIdBeer());
        return orders;
    }

    @Override
    public List<Order> findByCustomer(Customer customer){
      List<Order> orders =  jdbcTemplate.query("SELECT * FROM ORDERS WHERE CUSTOMERID = ?",
                this::mapRow, customer.getIdCustomer());
      orders.forEach(this::loadCustomer);
        return orders;
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

    @Override
    @Transactional
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM BEER_ORDER WHERE ORDER_ID = ?)", id);
        jdbcTemplate.update("DELETE FROM ORDERS WHERE IDORDER = ?", id);

        logger.debug("Deleting order with id: {}", id);
    }

    @Override
    @Transactional
    public void deleteBeer(int id, int beerId) {
        resetStock(beerId, id);
        jdbcTemplate.update("DELETE FROM BEER_ORDER WHERE ORDERID = ? AND BEERID = ?", id, beerId);

        logger.debug("Deleting order with id: {}", id);
    }

    public void resetStock(int beerId, int orderId){
        jdbcTemplate.query("SELECT * FROM BEER_ORDER WHERE ORDERID = ? AND BEERID = ?",
                (ResultSet beerRs, int beerRowNum) -> {
                    int quantity = beerRs.getInt("QUANTITY");
                    int currentStock = jdbcTemplate.queryForObject("SELECT STOCK FROM BEER WHERE IDBEER = ?", Integer.class, beerId);
                    int newStock = currentStock + quantity;

                    jdbcTemplate.update("UPDATE BEER SET STOCK = ? WHERE IDBEER = ?", newStock, beerId);
                    logger.info("Reset stock for beer with id: {} on {}, quantity {} and currentStock {}", beerId, newStock, quantity, currentStock);
                    return null;
                },
                orderId,
                beerId
        );
    }

}
