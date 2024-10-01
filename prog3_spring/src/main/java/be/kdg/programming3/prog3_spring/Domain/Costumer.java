package be.kdg.programming3.prog3_spring.Domain;

public class Costumer {
    private String contact;
    private String companyName;
    private String address;
    private String email;
    private String phone;

    private int idCostumer;

    public Costumer(String contact, String companyName, String address, String email, String phone) {
        this.contact = contact;
        this.companyName = companyName;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }
    public void setIdCostumer(int idCostumer) {
        this.idCostumer = idCostumer;
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

    public int getIdCostumer() {
        return idCostumer;
    }

    @Override
    public String toString() {
        return "Domain.Costumer{" +
                "contact='" + contact + '\'' +
                ", CompanyName='" + companyName + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", idCostumer=" + idCostumer +
                '}';
    }
}
