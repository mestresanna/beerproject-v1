package be.kdg.programming3.prog3_spring.presentation.export.dto;

import be.kdg.programming3.prog3_spring.Domain.*;


public class BeerDTO {

    private int idBeer;
    private String name;
    private String brewery;
    private double abv;
    private int plato;
    private String style;
    private Quantities quantity;
    private int stock;
    private Containers containers;
    private double price;

    public BeerDTO(Beer beer) {
        this.idBeer = beer.getIdBeer();
        this.name = beer.getName();
        this.brewery = beer.getBrewery();
        this.abv = beer.getAbv();
        this.plato = beer.getPlato();
        this.style = beer.getStyle();
        this.quantity = beer.getQuantity();
        this.stock = beer.getStock();
        this.containers = beer.getContainers();
        this.price = beer.getPrice();
    }

    @Override
    public String toString() {
        return "BeerDTO{" +
                "idBeer:" + idBeer +
                ", name:'" + name + '\'' +
                ", brewery:'" + brewery + '\'' +
                ", abv:" + abv +
                ", plato:" + plato +
                ", style:'" + style + '\'' +
                ", quantity:" + quantity +
                ", stock:" + stock +
                ", containers:" + containers +
                ", price:" + price +
                '}';
    }
}
