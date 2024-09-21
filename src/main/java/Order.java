import java.time.LocalDate;

public class Order {
    private int idOrder;
    private int customerId;
    private int productId;
    private int quantity;
    private LocalDate date;
    private String comments;

    public int getIdOrder() {
        return idOrder;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getComments() {
        return comments;
    }
}
