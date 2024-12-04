package be.kdg.programming3.prog3_spring.service;

import be.kdg.programming3.prog3_spring.Domain.Customer;
import be.kdg.programming3.prog3_spring.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class CustomerServiceImp implements CustomerService {
    private Logger logger = LoggerFactory.getLogger(CustomerServiceImp.class);
    private CustomerRepository customerRepository;
    public CustomerServiceImp(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void addCustomer(String contact, String companyName, String address, String email, String phone, String urlImg) {
        logger.info("Adding costumer " + contact);
        Customer customer = new Customer(contact, companyName, address, email, phone, urlImg);
        customerRepository.createCustomer(customer);
    }

    @Override
    public Customer getCustomer(int idCustomer) {
        return customerRepository.getCustomerById(idCustomer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }
}
