package be.kdg.programming3.prog3_spring.presentation.viewModels;

import be.kdg.programming3.prog3_spring.Domain.Containers;
import be.kdg.programming3.prog3_spring.Domain.Order;
import be.kdg.programming3.prog3_spring.Domain.Quantities;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.Optional;

@Validated
public class BeerViewModel {

    @NotBlank(message = "Name is required")
    private String name;

    private int idBeer;

    @Min(value = 0, message = "Alcohol must be 0 or higher")
    @Max(value = 100, message = "Alcohol must be 100 or lower")
    private double abv;

    private Optional<Integer> plato;

    @NotBlank(message = "Style is required")
    private String style;

    private Quantities quantity;

    @Min(value = 1, message = "Stock must be 0 or higher")
    private int stock;

    private Containers containers;

    @NotBlank(message = "Brewery is required")
    private String brewery;

    private ArrayList<Integer> orders;

    @Min(value = 0, message = "Price must be 0 or higher")
    private double price;

    private String imageUrl;

    public BeerViewModel() {
    }

    public BeerViewModel(String name, int idBeer, double abv, int plato, String style, Quantities quantity, int stock, Containers containers, String brewery, ArrayList<Integer> orders, double price, String imageUrl) {
        this.name = name;
        this.idBeer = idBeer;
        this.abv = abv;
        this.plato = Optional.ofNullable(plato);
        this.style = style;
        this.quantity = quantity;
        this.stock = stock;
        this.containers = containers;
        this.brewery = brewery;
        this.orders = orders;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public void setAbv(double abv) {
        this.abv = abv;
    }

    public void setPlato(Optional<Integer> plato) {
        this.plato = plato;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrewery(String brewery) {
        this.brewery = brewery;
    }

    public void setStyle(String style) {
        this.style = style;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public int getPlatoBeer(){
        return plato != null  && plato.isPresent() ? plato.get() : 0;
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

    public void setQuantity(Quantities quantity) {
        this.quantity = quantity;
    }

    public void setContainers(Containers containers) {
        this.containers = containers;
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

    public ArrayList<Integer> getOrders() {
        return orders;
    }

    public void setOrders(Integer order) {
        if (orders == null){
            orders = new ArrayList<>();
        }
        orders.add(order);
    }


    public void reduceStock(int quantity){
        this.stock-=quantity;
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
