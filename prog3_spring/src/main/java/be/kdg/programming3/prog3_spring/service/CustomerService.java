package be.kdg.programming3.prog3_spring.service;

import be.kdg.programming3.prog3_spring.Domain.Customer;

import java.util.List;

public interface CustomerService {
    void addCustomer(String contact, String companyName, String address, String email, String phone, String urlImg);

    Customer getCustomer(int idCustomer);

    List<Customer> getAllCustomers();
}
