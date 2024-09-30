package be.kdg.programming3.prog3_spring.Domain;

public class Costumer {
    private String contact;
    private String CompanyName;
    private String address;
    private String email;
    private String phone;

    private int idCostumer;

    public Costumer(String contact, String companyName, String address, String email, String phone, int idCostumer) {
        this.contact = contact;
        CompanyName = companyName;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.idCostumer = idCostumer;
    }
    public void setIdCostumer(int idCostumer) {
        this.idCostumer = idCostumer;
    }

    public String getContact() {
        return contact;
    }

    public String getCompanyName() {
        return CompanyName;
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

    public int getIdCostumer() {
        return idCostumer;
    }

    @Override
    public String toString() {
        return "Domain.Costumer{" +
                "contact='" + contact + '\'' +
                ", CompanyName='" + CompanyName + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", idCostumer=" + idCostumer +
                '}';
    }
}
