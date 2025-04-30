package be.kdg.programming3.prog3_spring.repository.jpa;

import be.kdg.programming3.prog3_spring.Domain.Customer;
import be.kdg.programming3.prog3_spring.exceptions.DataBaseException;
import be.kdg.programming3.prog3_spring.repository.CustomerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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
@Profile("jpa")
public class CustomerJPARepository implements CustomerRepository {

    private Logger logger = LoggerFactory.getLogger(CustomerJPARepository.class);

    @PersistenceContext
    private EntityManager em;

    public CustomerJPARepository() {}

    @Override
    @Transactional
    public Customer save(Customer customer) {
        customer.setImageUrl("/images/person.png");
        em.persist(customer);
        return customer;
    }

    @Override
    public Customer findById(int idCustomer) {
        logger.debug("Getting beer with id: {}", idCustomer);
        return em.find(Customer.class, idCustomer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        logger.debug("Getting all customers");
        List<Customer> customers = em.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
        return customers;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    @Transactional
    public void updateCustomer(Customer customer) {
        em.merge(customer);
    }

    @Override
    @Transactional
    public void delete(int id) {
        Customer customer = em.find(Customer.class, id);
        if (customer != null) {
            em.remove(customer);
            logger.debug("Deleted customer with id: {}", id);
        } else {
            logger.warn("Customer with id {} not found.", id);
        }
    }

}
