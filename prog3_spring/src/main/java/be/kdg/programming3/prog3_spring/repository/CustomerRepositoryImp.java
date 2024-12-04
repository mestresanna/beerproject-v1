package be.kdg.programming3.prog3_spring.repository;

import be.kdg.programming3.prog3_spring.Domain.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class CustomerRepositoryImp implements CustomerRepository {
    private static List<Customer> customers = new ArrayList<>();
    private Logger logger = LoggerFactory.getLogger(CustomerRepositoryImp.class);


    @Override
    public Customer createCustomer(Customer customer) {
        customers.add(customer);
        customer.setIdCustomer(customers.size()-1);
        logger.info("Creating new beer: {}, with id: {}", customer, customer.getIdCustomer());
        return customer;
    }

    @Override
    public Customer getCustomerById(int idCustomer) {
        logger.debug("Creating new beer: {}, with id: {}", customers.get(idCustomer), idCustomer);
        return customers.get(idCustomer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customers;
    }

    public int getSize(){
        return customers.size();
    }
}
