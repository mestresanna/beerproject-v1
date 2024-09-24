import java.rmi.MarshalledObject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private int idOrder;
    private int customerId;
    private int quantity;
    private LocalDate date;
    private String comments;
    private Costumer costumer;
    private Map beers = new HashMap();

    public int getIdOrder() {
        return idOrder;
    }

    public int getCustomerId() {
        return customerId;
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

    public void makeAnOrder(Costumer costumer, HashMap beers) {
        this.costumer = costumer;
        this.beers = beers;
    }

    @Override
    public String toString() {
        return "Order{" +
                "idOrder=" + idOrder +
                ", customerId=" + customerId +
                ", date=" + date +
                ", comments='" + comments + '\'' +
                ", costumer=" + costumer +
                ", beers=" + beers +
                '}';
    }
}
