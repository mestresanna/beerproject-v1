import java.security.KeyPair;
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

    private Map<String, ArrayList<String>> breweries = new HashMap<String, ArrayList<String>>();

    public Beer(){}

    public Beer(String name, int id, double abv, int plato, String style, Quantities quantity, int stock, String brewery) {
        this.name = name;
        this.id = id;
        this.abv = abv;
        this.plato = plato;
        this.style = style;
        this.quantity = quantity;
        this.stock = stock;
        addBeerToTheBrewery(brewery);
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

    public Quantities getQuantity() {
        return quantity;
    }

    public void addBeerToTheBrewery(String brewery) {

    }
}
