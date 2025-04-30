package be.kdg.programming3.prog3_spring.repository;

import be.kdg.programming3.prog3_spring.Domain.Customer;
import be.kdg.programming3.prog3_spring.exceptions.DataBaseException;
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
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("jdbc")
public class CustomerJBDCRepository implements CustomerRepository {

    private Logger logger = LoggerFactory.getLogger(CustomerJBDCRepository.class);
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert insertCustomer;

    public CustomerJBDCRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        insertCustomer = new SimpleJdbcInsert(jdbcTemplate).withTableName("customer").usingGeneratedKeyColumns("idcustomer");
    }

    @Override
    public Customer save(Customer customer) {
        PreparedStatementCreatorFactory pscf = new
                PreparedStatementCreatorFactory("INSERT INTO CUSTOMER(CONTACT, COMPANYNAME,ADDRESS, EMAIL, PHONE) VALUES (?,?,?,?,?)",
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR);

        pscf.setReturnGeneratedKeys(true);

        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
                List.of(customer.getContact(), customer.getCompanyName(), customer.getAddress(), customer.getEmail(), customer.getPhone()));
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(psc, keyHolder);
        customer.setIdCustomer(keyHolder.getKey().intValue());

        logger.debug("Creating new beer: {}, with id: {}", customer, customer.getIdCustomer());
        return customer;
    }

    @Override
    public Customer findById(int idCustomer) {
        logger.debug("Getting beer with id: {}", idCustomer);
        return jdbcTemplate.queryForObject("SELECT * FROM CUSTOMER WHERE IDCUSTOMER = ?", this::mapRow, idCustomer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        logger.debug("Getting all customers");
        List<Customer> customers = new ArrayList<>();

        jdbcTemplate.query("SELECT * FROM CUSTOMER",
                (ResultSet rs) -> {
                    int idCustomer = rs.getInt("idcustomer");
                    String contact = rs.getString("contact");
                    String companyname = rs.getString("companyname");
                    String address = rs.getString("address");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    String imageUrl = "/images/person.png";

                    Customer customer = new Customer(idCustomer, contact, companyname, address, email, phone, imageUrl);
                    customers.add(customer);
                });
        return customers;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public void updateCustomer(Customer customer) {
        try{
            jdbcTemplate.update("UPDATE CUSTOMER SET CONTACT = ?, COMPANYNAME = ?, ADDRESS = ?, EMAIL = ?, PHONE = ? WHERE IDCUSTOMER = ?",
                    customer.getContact(), customer.getCompanyName(), customer.getAddress(), customer.getEmail(), customer.getPhone(), customer.getIdCustomer());
            logger.debug("Customer updated: {}", customer);
            logger.info("Customer updated: {} , orderlist: {}", customer,customer.getOrders());
        } catch (DataAccessException e){
            logger.error(e.getMessage());
            throw new DataBaseException("Error updating beer: " + e);
        }
    }

    private Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        String imageUrl = "/images/person.png";
        return new Customer(rs.getInt("idcustomer"),rs.getString("contact"), rs.getString("companyname"), rs.getString("address"), rs.getString("email"), rs.getString("phone"), imageUrl);
    }

    @Override
    @Transactional
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM BEER_ORDER WHERE ORDERID " +
                "in (SELECT ORDERID FROM ORDERS WHERE CUSTOMERID = ?)", id);
        jdbcTemplate.update("DELETE FROM ORDERS WHERE CUSTOMERID = ? ", id);
        jdbcTemplate.update("DELETE FROM CUSTOMER WHERE IDCUSTOMER = ? ", id);

        logger.debug("Deleting customer with id: {}", id);
    }

}
