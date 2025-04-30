package be.kdg.programming3.prog3_spring.presentation.viewModels;

import be.kdg.programming3.prog3_spring.Domain.Order;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;

@Validated
public class CustomerViewModel {
    @NotBlank(message = "Contact Name is required")
    private String contact;

    @NotBlank(message = "Company Name is required")
    private String companyName;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Email is required")
    @Email
    private String email;

    @NotBlank(message = "Phone is required")
    private String phone;

    private String imageUrl;
    private ArrayList<Integer> orders;



    private int idCustomer;

    public CustomerViewModel() {}
    public CustomerViewModel(String contact, String companyName, String address, String email, String phone, String imageUrl) {
        this.contact = contact;
        this.companyName = companyName;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.imageUrl = imageUrl;
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
    public ArrayList<Integer> getOrders() {
        return orders;
    }

    public void setOrders(Integer order) {
        if (orders == null){
            orders = new ArrayList<>();
        }
        orders.add(order);
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
