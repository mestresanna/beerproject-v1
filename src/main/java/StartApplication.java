public class StartApplication {
    public static void main(String[] args) {

        Beer beer = new Beer(324, "Vault");
        Beer beer2 = new Beer(456, "Storm");

        System.out.println(beer.toStringBreweries("Vault"));
        System.out.println(beer2.toStringBreweries("Storm"));


        System.out.println(beer2.toStringBreweriesAmount());


    }
}
