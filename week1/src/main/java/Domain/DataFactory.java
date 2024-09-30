package Domain;

import java.util.ArrayList;

//Beers has a many to many relationship with Orders, Costumers has one to many with Orders

public class DataFactory {
    //public static ArrayList<Domain.Costumer> costumers = new ArrayList();
    public static ArrayList<Beer> beers = new ArrayList();
    public static ArrayList<Order> orders = new ArrayList();

    public static void seed() {
        //add manually the things
    }

    static {
        seed();
    }

}
