package be.kdg.programming3.prog3_spring.presentation.viewModels;

import be.kdg.programming3.prog3_spring.Domain.Beer;
import be.kdg.programming3.prog3_spring.Domain.Customer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderViewModel {
    private int idOrder;
    private LocalDate date;
    private String comments;
    private int customer;
    private HashMap<Beer, Integer> beers;
    private List<BeerStockEntry> beersList = new ArrayList<>();
    private double total;
    private String imageUrl;
    private int stock;

    public OrderViewModel(){}

    public OrderViewModel(String comments, int customer, HashMap<Beer, Integer> beers, String imageUrl) {
        this.comments = comments;
        this.customer = customer;
        this.beers = beers;
        setTotalPrice();
        setStockToBeers();
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

    public void setCustomer(int customer) {
        this.customer = customer;
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

    public void setStockToBeers(){
        if (beers!=null && beers.size()>0) {
            for (Map.Entry<Beer, Integer> entry : beers.entrySet()) {
                Beer key = entry.getKey();
                Integer value = entry.getValue();
                key.reduceStock(value);
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

    public void setBeers(Beer beer, int stock) {
        if (beers == null || beers.size()==0) {
            this.beers = new HashMap<>();
        }
        this.beers.put(beer, stock);
        this.beers = beers;
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

    public int getCustomer() {
        return customer;
    }

    @Override
    public String toString() {
        return "Order { costumer=" + customer + ", beers=" + beers +
                ", date=" + date + ", comments='" + comments + '}';
    }


    public List<BeerStockEntry> getBeersList() {
        return beersList;
    }

    public void setBeersList(List<BeerStockEntry> beersList) {
        this.beersList = beersList;
    }

    public void populateBeersMap() {
        this.beers = new HashMap<>();
        for (BeerStockEntry entry : beersList) {
            this.beers.put(entry.getBeer(), entry.getStock());
        }
    }
}
