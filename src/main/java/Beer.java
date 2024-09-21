import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Beer {
    private String name;
    private int id;
    private double abv;
    private int plato;
    private String style;
    private Quantities quantity;
    private int stock;
    private Containers containers;
    private String brewery;



    public enum Quantities {
            NIP("7oz"), STUBBY("12oz"), LONGNECK("12oz"), BELGIAN("375ml"), BRITISH("500ml"), BOMBER("650ml"), LARGE_FORMAT("750ml"), CAGUAMA("940ml"), HOWLER("32oz"),
        TALLBOY("16oz"), STOVEPIPE("19,2oz"), CROWLER("32oz");

        private final String size;

        // private enum constructor
        private Quantities(String size) {
            this.size = size;
        }

        public String getSize() {
            return size;
        }
    }

    public enum Containers {
        BOTTLE("bottle"), CAN("can"), KEG("keg");

        private final String container;

        // private enum constructor
        private Containers(String container) {
            this.container = container;
        }

        public String getContainer() {
            return container;
        }
    }

    public Beer(String name, int id, double abv, int plato, String style, Quantities quantity, int stock, Containers containers, String breweries) {
        this.name = name;
        this.id = id;
        this.abv = abv;
        this.plato = plato;
        this.style = style;
        this.quantity = quantity;
        this.stock = stock;
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

    public int getPlato() {
        return plato;
    }

    public String getStyle() {
        return style;
    }

    public int getStock() {
        return stock;
    }


    @Override
    public String toString() {
        return brewery + " - " + name + " - " + containers.getContainer() + quantity.getSize()
                + "(" + abv + "%, " + style   + ") " + ", stock=" + stock ;
    }



}
