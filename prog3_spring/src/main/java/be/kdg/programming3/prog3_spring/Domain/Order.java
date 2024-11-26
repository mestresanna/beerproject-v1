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
    private int total;
    private String imageUrl;

    public Order(LocalDate date, String comments, Costumer costumer, HashMap<Beer, Integer> beers, String imageUrl) {
        this.date = date;
        this.comments = comments;
        this.costumer = costumer;
        this.beers = beers;
        setTotalPrice();
        setStockToBeer();
        this.imageUrl = imageUrl;
    }

    public int getTotal() {
        return total;
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

    public void setTotal(int total) {
        this.total = total;
    }

    public void setTotalPrice() {
        setTotal(0);
        int price = 0;
        if (beers!=null && beers.size()>0) {
            for (Map.Entry<Beer, Integer> entry : beers.entrySet()) {
                Beer key = entry.getKey();
                Integer value = entry.getValue();
                price += key.getPrice() * value;
            }
        }
        setTotal(price);
        //call an error when beers is empty
    }

    public void setStockToBeer(){
       if (beers!=null && beers.size()>0) {
           for (Map.Entry<Beer, Integer> entry : beers.entrySet()) {
               Beer key = entry.getKey();
               Integer value = entry.getValue();
               key.setStock(value);
           }
       }
       //call an error when beers is empty
    }

    public int getStockToBeer(Beer beer){
        if (beers!=null && beers.size()>0) {
            for (Map.Entry<Beer, Integer> entry : beers.entrySet()) {
                Beer key = entry.getKey();
                if (key == beer) {
                    return entry.getValue();
                }
            }
        }
        return 0;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public HashMap<Beer, Integer> getBeers() {
        return beers;
    }

    public Costumer getCostumer() {
        return costumer;
    }

    @Override
    public String toString() {
        return "Order { costumer=" + costumer + ", beers=" + beers +
                ", date=" + date + ", comments='" + comments + '}';
    }
}
