package be.kdg.programming3.prog3_spring.Domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Beer {
    //implements beer
    private String name;
    private int idBeer;
    private double abv;
    private Optional<Integer> plato;
    private String style;
    private Quantities quantity;
    private int stock;
    private Containers containers;
    private String brewery;
    private ArrayList<Order> orders = new ArrayList<>();
    private double price;
    private String imageUrl;

    public Beer(String name, double abv, int plato, String style, Quantities quantity, int stock, Containers containers, String brewery, double price, String imageUrl) {
        this.name = name;
        this.abv = abv;
        this.plato = Optional.of(plato);
        this.style = style;
        this.quantity = quantity;
        this.stock = stock;
        this.containers = containers;
        this.brewery=brewery;
        this.price = price;
        this.imageUrl = imageUrl;
    }


    public Beer(int id, String name, double abv, Optional<Integer> plato, String style, Quantities quantity, int stock, Containers containers, String brewery, double price, String imageUrl) {
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

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAbv(double abv) {
        this.abv = abv;
    }

    public void setPlato(Optional<Integer> plato) {
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

    public Optional<Integer> getPlato() {
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

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrder(Order order) {
        if (orders == null){
            orders = new ArrayList<>();
        }
        orders.add(order);
    }

    public void setOrders(List<Order> orders) {
        this.orders = (ArrayList<Order>) orders;
    }

    public void reduceStock(int quantity){
        if (this.stock - quantity < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }
        this.stock -= quantity;
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



}
