import java.util.ArrayList;

//Beers has a many to many relationship with Orders, Costumers has one to many with Orders

public class DataFactory {
    public static ArrayList Costumers = new ArrayList();
    public static ArrayList beers = new ArrayList();
    public static ArrayList orders = new ArrayList();

    public void seed(ArrayList arrayList, String item) {
        arrayList.add(item);
    }


}
