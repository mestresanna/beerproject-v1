package be.kdg.programming3.prog3_spring.Domain;

import jakarta.persistence.*;

import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "BEER")
public class Beer {

    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBeer;
    private double abv;
    private int plato;
    private String style;

    @Enumerated(EnumType.STRING)
    private Quantities quantity;
    private int stock;

    @Enumerated(EnumType.STRING)
    private Containers containers;
    private String brewery;

    @Transient
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "beer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderBeer> orderBeers = new HashSet<>();

    private double price;

    private String imageUrl;

    public Beer(String name, double abv, int plato, String style, Quantities quantity, int stock, Containers containers, String brewery, double price, String imageUrl) {
        this.name = name;
        this.abv = abv;
        this.plato = plato;
        this.style = style;
        this.quantity = quantity;
        this.stock = stock;
        this.containers = containers;
        this.brewery=brewery;
        this.price = price;
        this.imageUrl = imageUrl;
    }


    public Beer(int id, String name, double abv, int plato, String style, Quantities quantity, int stock, Containers containers, String brewery, double price, String imageUrl) {
        this.idBeer = id;
        this.name = name;
        this.abv = abv;
        this.plato = plato;
        this.style = style;
        this.quantity = quantity;
        this.stock = stock;
        this.containers = containers;
        this.brewery=brewery;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Beer() {

    }


    public Set<OrderBeer> getOrderBeers() {
        return orderBeers;
    }

    public void setOrderBeers(Set<OrderBeer> orderBeers) {
        this.orderBeers = orderBeers;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAbv(double abv) {
        this.abv = abv;
    }

    public void setPlato(int plato) {
        this.plato = plato;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public void setQuantity(Quantities quantity) {
        this.quantity = quantity;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setContainers(Containers containers) {
        this.containers = containers;
    }

    public void setBrewery(String brewery) {
        this.brewery = brewery;
    }


    public void setPrice(double price) {
        this.price = price;
    }

    public void setIdBeer(int idBeer) {
        this.idBeer = idBeer;
    }

    public String getName() {
        return name;
    }

    public int getIdBeer() {
        return idBeer;
    }

    public double getAbv() {
        return abv;
    }

    public int getPlato() {
        return plato;
    }

    public String getStyle() {
        return style;
    }

    public int getStock() {
        return stock;
    }

    public Quantities getQuantity() {
        return quantity;
    }

    public Containers getContainers() {
        return containers;
    }

    public String getBrewery() {
        return brewery;
    }

    public List<Order> getOrders() {
        //This allows us to use just one method for all profiles
        if (orderBeers != null) {
            return orderBeers.stream()
                    .map(OrderBeer::getOrder)
                    .distinct()
                    .collect(Collectors.toList());
        }
        return orders;
    }

    public void setOrder(Order order) {
        if (orders == null){
            orders = new ArrayList<>();
        }
        orders.add(order);
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public String toString() {
        return brewery + " - " + name + " - " + containers.getName() + " " + quantity.getSize();
    }

    public void addOrderBeer(OrderBeer orderBeer) {
        orderBeers.add(orderBeer);
        orderBeer.setBeer(this);
    }

    public void removeOrderBeer(OrderBeer orderBeer) {
        orderBeers.remove(orderBeer);
        orderBeer.setBeer(null);
    }


}
