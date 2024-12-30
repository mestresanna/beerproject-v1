package be.kdg.programming3.prog3_spring.repository;

import be.kdg.programming3.prog3_spring.Domain.Customer;

import java.util.List;

public interface CustomerRepository {
    Customer save(Customer customer);

    Customer findById(int idCustomer);

    List<Customer> getAllCustomers();

    int getSize();

    void updateCustomer(Customer customer);
}
