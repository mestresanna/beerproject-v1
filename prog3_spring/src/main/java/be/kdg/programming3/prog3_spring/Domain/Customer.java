package be.kdg.programming3.prog3_spring.Domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "CUSTOMER")
public class Customer {
    private String contact;
    private String companyName;
    private String address;
    private String email;
    private String phone;
    private String imageUrl;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCustomer;

    public Customer(int idCustomer, String contact, String companyName, String address, String email, String phone, String imageUrl ) {
        this.contact = contact;
        this.companyName = companyName;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.idCustomer = idCustomer;
        this.imageUrl = imageUrl;
    }

    public Customer(String contact, String companyName, String address, String email, String phone, String imageUrl) {
        this.contact = contact;
        this.companyName = companyName;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.imageUrl = imageUrl;
    }

    public Customer() {
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getContact() {
        return contact;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public int getIdCustomer() {
        return idCustomer;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(Order order) {
        if (orders == null){
            orders = new ArrayList<>();
        }
        orders.add(order);
    }

    public void setOrders(List<Order> orders) {
        this.orders = (ArrayList<Order>) orders;
    }

    @Override
    public String toString() {
        return "Domain.Customer{" +
                "contact='" + contact + '\'' +
                ", CompanyName='" + companyName + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", idCustomer=" + idCustomer +
                '}';
    }
}
