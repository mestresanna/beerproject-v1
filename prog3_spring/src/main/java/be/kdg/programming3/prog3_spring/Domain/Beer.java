package be.kdg.programming3.prog3_spring.Domain;

import java.util.ArrayList;
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
    private ArrayList<Order> orders;
    private double price;

    public Beer(String name, double abv, int plato, String style, Quantities quantity, int stock, Containers containers, String brewery, double price) {
        this.name = name;
        this.abv = abv;
        this.plato = Optional.of(plato);
        this.style = style;
        this.quantity = quantity;
        this.stock = stock;
        this.containers = containers;
        this.brewery=brewery;
        this.price = price;
    }
/*
    public Beer(String name, double abv, String style, Quantities quantity, int stock, Containers containers, String brewery, int price) {
        this.name = name;
        this.abv = abv;
        this.plato = Optional.empty();
        this.style = style;
        this.quantity = quantity;
        this.stock = stock;
        this.containers = containers;
        this.brewery=brewery;
        this.price = price;
    }*/

    public double getPrice() {
        return price;
    }

    public int getPlatoBeer(){
        if (plato.get()==null){
            return 0;
        }
        else {
            return plato.get();
        }
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

    public void setOrders(Order order) {
        if (orders == null){
            orders = new ArrayList<>();
        }
        orders.add(order);
    }

    public void setStock(int quantity){
        stock-=quantity;
    }

    @Override
    public String toString() {
        return brewery + " - " + name + " - " + containers.getName() + " " + quantity.getSize() + " " + stock;
    }



}
