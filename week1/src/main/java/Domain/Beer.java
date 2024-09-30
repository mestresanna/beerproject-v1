package Domain;

import java.util.Optional;

public class Beer {
    //implements beer
    private String name;
    private int id;
    private double abv;
    private Optional<Integer> plato;
    private String style;
    private Quantities quantity;
    private Optional<Integer> stock;
    private Containers containers;
    private String brewery;

    public Beer(String name, int id, double abv, int plato, String style, Quantities quantity, int stock, Containers containers, String brewery) {
        this.name = name;
        this.id = id;
        this.abv = abv;
        this.plato = Optional.of(plato);
        this.style = style;
        this.quantity = quantity;
        this.stock = Optional.of(stock);
        this.containers = containers;
        this.brewery=brewery;
    }
    public Beer(String name, int id, double abv, String style, Quantities quantity, int stock, Containers containers, String brewery) {
        this.name = name;
        this.id = id;
        this.abv = abv;
        this.plato = Optional.empty();
        this.style = style;
        this.quantity = quantity;
        this.stock = Optional.of(stock);
        this.containers = containers;
        this.brewery=brewery;
    }

    public Beer(String name, int id, double abv, int plato, String style, Quantities quantity, Containers containers, String brewery) {
        this.name = name;
        this.id = id;
        this.abv = abv;
        this.plato = Optional.of(plato);
        this.style = style;
        this.quantity = quantity;
        this.stock = Optional.empty();
        this.containers = containers;
        this.brewery=brewery;
    }

    public Beer(String name, int id, double abv, String style, Quantities quantity, Containers containers, String brewery) {
        this.name = name;
        this.id = id;
        this.abv = abv;
        this.plato = Optional.empty();
        this.style = style;
        this.quantity = quantity;
        this.stock = Optional.empty();
        this.containers = containers;
        this.brewery=brewery;
    }


    public String getName() {
        return name;
    }

    public int getId() {
        return id;
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

    public Optional<Integer> getStock() {
        return stock;
    }


    @Override
    public String toString() {
        return brewery + " - " + name + " - " + containers.getContainer() + quantity.getSize()
                + "(" + abv + "%, " + style   + ") " + ", stock=" + stock ;
    }



}
