package be.kdg.programming3.prog3_spring.service.jpa;

import be.kdg.programming3.prog3_spring.Domain.Customer;
import be.kdg.programming3.prog3_spring.repository.CustomerRepository;
import be.kdg.programming3.prog3_spring.repository.jpa.CustomerRepositoryJPA;
import be.kdg.programming3.prog3_spring.service.CustomerService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("jpa_rep")
public class CustomerServiceJPA implements CustomerService {
    private Logger logger = LoggerFactory.getLogger(CustomerServiceJPA.class);
    private CustomerRepositoryJPA customerRepository;

    public CustomerServiceJPA(CustomerRepositoryJPA customerRepository) {
        this.customerRepository = customerRepository;
    }
    @Transactional
    @Override
    public void addCustomer(String contact, String companyName, String address, String email, String phone, String urlImg) {
        logger.info("Adding costumer " + contact);
        Customer customer = new Customer(contact, companyName, address, email, phone, urlImg);
        customerRepository.save(customer);
    }

    @Override
    public Customer getCustomer(int idCustomer) {
        return customerRepository.findById(idCustomer).orElse(null);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Transactional
    @Override
    public void updateCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Transactional
    @Override
    public void delete(int id){
        customerRepository.deleteById(id);
    }
}
