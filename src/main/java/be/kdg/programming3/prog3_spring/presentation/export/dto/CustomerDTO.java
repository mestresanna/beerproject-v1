package be.kdg.programming3.prog3_spring.presentation.export.dto;

import be.kdg.programming3.prog3_spring.Domain.Customer;



public class CustomerDTO {
    private int idCustomer;
    private String contact;
    private String companyName;
    private String address;
    private String email;
    private String phone;


    public CustomerDTO(Customer customer) {
        this.idCustomer = customer.getIdCustomer();
        this.contact = customer.getContact();
        this.companyName = customer.getCompanyName();
        this.address = customer.getAddress();
        this.email = customer.getEmail();
        this.phone = customer.getPhone();
    }
}
