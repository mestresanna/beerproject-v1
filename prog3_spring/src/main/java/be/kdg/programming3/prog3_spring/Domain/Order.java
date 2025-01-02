package be.kdg.programming3.prog3_spring.Domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idOrder;
    private LocalDate date;
    private String comments;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType. DETACH,
            CascadeType. MERGE, CascadeType. PERSIST,
            CascadeType. REFRESH})
    @JoinColumn(name = "idCustomer")
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderBeer> orderBeers = new HashSet<>();

    @Transient
    private HashMap<Beer, Integer> beers;

    private double total;
    private String imageUrl;

    public Order() {}

    public Order(int id, String comments,LocalDate date,  Customer customer, HashMap<Beer, Integer> beers, String imageUrl) {
        this.idOrder = id;
        this.comments = comments;
        this.date = date;
        this.customer = customer;
        if (beers != null) {
            for (Map.Entry<Beer, Integer> entry : beers.entrySet()) {
                this.addBeer(entry.getKey(), entry.getValue());
            }
            this.beers=beers;
        }
        setTotalPrice();
        this.imageUrl = imageUrl;
    }

    public Order(String comments, Customer customer, HashMap<Beer, Integer> beers, String imageUrl) {
        this.comments = comments;
        this.customer = customer;
        if (beers != null) {
            for (Map.Entry<Beer, Integer> entry : beers.entrySet()) {
                this.addBeer(entry.getKey(), entry.getValue());
            }
            this.beers=beers;
        }
        setTotalPrice();
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
        //customer.setOrders(this);
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

    public int getStockToBeer(int beer){
        AtomicInteger value= new AtomicInteger();
        if (beers!=null && beers.size()>0) {
            for (Map.Entry<Beer, Integer> entry : beers.entrySet()) {
                Beer key = entry.getKey();
                if (key.getIdBeer() == beer) {
                    value.set(entry.getValue());
                }
            }
        }

        if (orderBeers!=null && orderBeers.size()>0) {
            orderBeers.forEach(orderBeer -> {
                if (orderBeer.getBeer().getIdBeer() == beer) {
                    value.set(orderBeer.getQuantity());
                }
            });
        }
        return value.get();
    }

    public Set<OrderBeer> getOrderBeers() {
        return orderBeers;
    }


    public void setOrderBeers(Set<OrderBeer> orderBeers) {
        this.orderBeers = orderBeers;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public HashMap<Beer, Integer> getBeers() {
        if (orderBeers!=null) {
            HashMap<Beer, Integer> beersOrder = new HashMap<>(orderBeers.size());
            orderBeers.forEach(orderBeer -> {
                Beer beer = orderBeer.getBeer();
                int quantity = orderBeer.getQuantity();
                beersOrder.put(beer, quantity);
            });
            return beersOrder;
        }
        return beers;
    }

    public List<Beer> getBeersFromOrder() {
        if (getBeers()!=null) {
            List<Beer> beers = new ArrayList<>(this.getBeers().keySet());
            return beers;
        }
        return null;
    }

    public Customer getCustomer() {
        return customer;
    }

    @Override
    public String toString() {
        return "Order { costumer=" + customer + ", beers=" + beers +
                ", date=" + date + ", comments='" + comments + '}';
    }

    public void addOrderBeer(OrderBeer orderBeer) {
        orderBeers.add(orderBeer);
        orderBeer.setOrder(this);
    }

    public void removeOrderBeer(OrderBeer orderBeer) {
        orderBeers.remove(orderBeer);
        orderBeer.setOrder(null);
    }

    public void addBeer(Beer beer, int quantity) {
        OrderBeer orderBeer = new OrderBeer(this, beer, quantity);
        addOrderBeer(orderBeer);
        beer.addOrderBeer(orderBeer);
        //beer.setStock(beer.getStock() - quantity);
    }

    public void removeBeer(Beer beer) {
        for (Iterator<OrderBeer> iterator = orderBeers.iterator(); iterator.hasNext();) {
            OrderBeer orderBeer = iterator.next();
            if (orderBeer.getOrder().equals(this) && orderBeer.getBeer().equals(beer)) {
                iterator.remove();
                orderBeer.getBeer().getOrderBeers().remove(orderBeer);
                orderBeer.setOrder(null);
                orderBeer.setBeer(null);
            }
        }
    }
}
