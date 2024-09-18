import java.time.LocalDate;

public class Order {
    private int id;
    private int customerId;
    private int productId;
    private int quantity;
    private LocalDate date;

    public int getId() {
        return id;
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
}
