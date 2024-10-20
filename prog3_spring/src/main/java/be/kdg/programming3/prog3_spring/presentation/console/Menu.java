package be.kdg.programming3.prog3_spring.presentation.console;

import be.kdg.programming3.prog3_spring.Domain.Beer;
import be.kdg.programming3.prog3_spring.Domain.Containers;
import be.kdg.programming3.prog3_spring.Domain.Costumer;
import be.kdg.programming3.prog3_spring.Domain.Quantities;
import be.kdg.programming3.prog3_spring.service.BeerService;
import be.kdg.programming3.prog3_spring.service.CostumerService;
import be.kdg.programming3.prog3_spring.service.OrderService;

import java.util.HashMap;
import java.util.Scanner;


public class Menu {

    private OrderService orderService;
    private BeerService beerService;
    private CostumerService costumerService;
    private Scanner scanner = new Scanner(System.in);

    public Menu(OrderService orderService, BeerService beerService,CostumerService costumerService) {
        this.orderService = orderService;
        this.beerService = beerService;
        this.costumerService = costumerService;
    }

    public void show() {
        while(true) {
            System.out.println("1. Add Beer");
            System.out.println("2. List all beers");
            System.out.println("3. Add Order");
            System.out.println("4. List all orders");
            System.out.println("5. Add Costumer");
            System.out.println("6. List all costumers");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> addBeer();
                case 2 -> listBeers();
                case 3 -> addOrder();
                case 4 -> listOrders();
                case 5 -> addCostumer();
                case 6 -> listCostumers();
            }
        }
    }

    private void addBeer(){
        Quantities quantities = Quantities.BELGIAN;
        Containers containers = Containers.CAN;
        System.out.println("Enter beer's name: ");
        String name = scanner.nextLine();
        System.out.println("Enter beer's alcohol: ");
        double abv = scanner.nextDouble();
        System.out.println("Enter beer's plato: ");
        int plato = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter beer's style: ");
        String style = scanner.nextLine();
        System.out.println("Enter beer's stock: ");
        int stock = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter beer's brewery: ");
        String brewery = scanner.nextLine();
        System.out.println("Enter beer's price: ");
        int price = scanner.nextInt();
        Beer beer = new Beer(name, abv, plato, style, quantities, stock, containers, brewery, price);
        beerService.addBeer(beer);
    }

    private void listBeers(){
        beerService.getAllBeers().forEach(System.out::println);
    }

    private void addOrder(){
        System.out.println("Enter idCostumer: ");
        int idCostumer = scanner.nextInt();
        Costumer costumer = costumerService.getCostumer(idCostumer);
        System.out.println("Enter Comments: ");
        String comments = scanner.nextLine();
        HashMap<Beer, Integer> beers = new HashMap<>();
        makeListBeers(beers);
        orderService.addOrder(comments, costumer,beers);

    }

    private boolean makeListBeers(HashMap<Beer, Integer> beers){
        String answer;
        if (!beerService.getAllBeers().isEmpty()) {
            while (true) {
                System.out.println("Do you want to enter a beer? (Y/N)");
                answer = scanner.next();
                scanner.nextLine();
                if (answer.equals("Y")) {
                    System.out.println("Enter beer's id: ");
                    int idBeer = scanner.nextInt();
                    System.out.println("Enter amount: ");
                    int amount = scanner.nextInt();
                    Beer beer = beerService.getBeerById(idBeer);
                    beers.put(beer, amount);
                }
                if (answer.equals("N") && !beers.isEmpty()) {
                    return false;
                } else {
                    System.out.println("That is not a valid option or you must add at least one beer");
                }
            }
        } else {
            System.out.println("There are no beers in the system yet, add them first!");
            return false;
        }
    }

    private void listOrders(){
        orderService.getAllOrders().forEach(System.out::println);
    }

    private void addCostumer() {
        System.out.println("Enter contact name: ");
        String contact = scanner.nextLine();
        System.out.println("Enter Company Name: ");
        String company = scanner.nextLine();
        System.out.println("Enter Address: ");
        String address = scanner.nextLine();
        System.out.println("Enter Phone Number: ");
        String phone = scanner.nextLine();
        System.out.println("Enter Email: ");
        String email = scanner.nextLine();
        costumerService.addCostumer(contact, company, address, email, phone);
    }

    private void listCostumers(){
        costumerService.getAllCostumers().forEach(System.out::println);
    }
}
