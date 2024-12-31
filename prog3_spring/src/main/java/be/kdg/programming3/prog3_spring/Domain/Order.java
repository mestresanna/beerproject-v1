package be.kdg.programming3.prog3_spring.Domain;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private int idOrder;
    private LocalDate date;
    private String comments;
    private Customer customer;
    private HashMap<Beer, Integer> beers;
    private double total;
    private String imageUrl;

    public Order(int id, String comments,LocalDate date,  Customer customer, HashMap<Beer, Integer> beers, String imageUrl) {
        this.idOrder = id;
        this.comments = comments;
        this.date = date;
        this.customer = customer;
        this.beers = beers;
        setTotalPrice();
        //setStockToBeer();
        this.imageUrl = imageUrl;
    }

    public Order(String comments, Customer customer, HashMap<Beer, Integer> beers, String imageUrl) {
        this.comments = comments;
        this.customer = customer;
        this.beers = beers;
        setTotalPrice();
        //setStockToBeer();
        this.imageUrl = imageUrl;
    }

    public double getTotal() {
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

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        customer.setOrders(this);
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setTotalPrice() {
        setTotal(0);
        double price = 0;
        if (beers!=null && beers.size()>0) {
            for (Map.Entry<Beer, Integer> entry : beers.entrySet()) {
                Beer key = entry.getKey();
                Integer value = entry.getValue();
                price += (key.getPrice() * value);
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
               key.reduceStock(value);
           }
       }
       //call an error when beers is empty
    }

    public int getStockToBeer(Integer beer){
        if (beers!=null && beers.size()>0) {
            for (Map.Entry<Beer, Integer> entry : beers.entrySet()) {
                Beer key = entry.getKey();
                if (key.getIdBeer() == beer) {
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

    public Customer getCustomer() {
        return customer;
    }

    @Override
    public String toString() {
        return "Order { costumer=" + customer + ", beers=" + beers +
                ", date=" + date + ", comments='" + comments + '}';
    }
}
