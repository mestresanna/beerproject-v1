package be.kdg.programming3.prog3_spring.Domain;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.ErrorManager;

public class Order {
    private int idOrder;
    private LocalDate date;
    private String comments;
    private Costumer costumer;
    private HashMap<Beer, Integer> beers;

    public Order(LocalDate date, String comments, Costumer costumer, HashMap<Beer, Integer> beers) {
        this.date = date;
        this.comments = comments;
        this.costumer = costumer;
        this.beers = beers;
        setOrderToBeer();
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getComments() {
        return comments;
    }

    public void setOrderToBeer(){
       if (beers!=null && beers.size()>0) {
           for (Map.Entry<Beer, Integer> entry : beers.entrySet()) {
               Beer key = entry.getKey();
               Integer value = entry.getValue();
               key.setStock(value);
           }
       }
       //call an error when beers is empty
    }

    @Override
    public String toString() {
        return "Order { costumer=" + costumer + ", beers=" + beers +
                ", date=" + date + ", comments='" + comments + '}';
    }
}
