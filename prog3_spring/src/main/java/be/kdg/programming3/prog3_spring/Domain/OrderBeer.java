package be.kdg.programming3.prog3_spring.Domain;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "ORDER_BEER")
public class OrderBeer {
    @EmbeddedId
    private OrderBeerId id;

    @ManyToOne(cascade = {CascadeType. DETACH,
            CascadeType. MERGE, CascadeType. PERSIST,
            CascadeType. REFRESH})
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(cascade = {CascadeType. DETACH,
            CascadeType. MERGE, CascadeType. PERSIST,
            CascadeType. REFRESH})
    @MapsId("beerId")
    @JoinColumn(name = "beer_id")
    private Beer beer;

    private int quantity;


    public OrderBeer() {
        this.id = new OrderBeerId();
    }

    public OrderBeer(Order order, Beer beer, int quantity) {
        this.order = order;
        this.beer = beer;
        this.quantity = quantity;
        this.id = new OrderBeerId(order.getIdOrder(), beer.getIdBeer());
    }

    public void addBeer(Beer beer) {
        //it allows to reset de stock
        Set<OrderBeer> orderBeers = new HashSet<>();
        if (beer.getOrderBeers() != null) {
            orderBeers.addAll(beer.getOrderBeers());
        }
        orderBeers.add(this);
        beer.setOrderBeers(orderBeers);
        this.beer = beer;
    }

    public OrderBeerId getId() {
        return id;
    }

    public void setId(OrderBeerId id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Beer getBeer() {
        return beer;
    }

    public void setBeer(Beer beer) {
        this.beer = beer;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderBeer orderBeer)) return false;
        return Objects.equals(id, orderBeer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
