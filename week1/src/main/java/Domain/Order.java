package Domain;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private int idOrder;
    private int customerId;
    private int quantity;
    private LocalDate date;
    private String comments;

    private Costumer costumer;
    private Map<Beer, Integer> beers = new HashMap(); //inicialitzar amb control de que no sigui null en un method

    //set id perque la database ho introdueixi directament

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
        return "Domain.Order{" +
                "idOrder=" + idOrder +
                ", customerId=" + customerId +
                ", date=" + date +
                ", comments='" + comments + '\'' +
                ", costumer=" + costumer +
                ", beers=" + beers +
                '}';
    }
}
