package be.kdg.programming3.prog3_spring.repository;

import be.kdg.programming3.prog3_spring.Domain.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CustomerRepository {
    Customer createCustomer(Customer customer);

    Customer getCustomerById(int idCustomer);

    List<Customer> getAllCustomers();

    int getSize();
}
