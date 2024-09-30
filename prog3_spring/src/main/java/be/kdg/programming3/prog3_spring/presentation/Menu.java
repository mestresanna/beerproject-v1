package be.kdg.programming3.prog3_spring.presentation;

import be.kdg.programming3.prog3_spring.Domain.Containers;
import be.kdg.programming3.prog3_spring.Domain.Quantities;
import be.kdg.programming3.prog3_spring.service.BeerService;
import be.kdg.programming3.prog3_spring.service.OrderService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Menu {

    private OrderService orderService;
    private BeerService beerService;
    private Scanner scanner = new Scanner(System.in);

    public Menu(OrderService orderService, BeerService beerService) {
        this.orderService = orderService;
        this.beerService = beerService;
    }

    public void show() {
        while(true) {
            System.out.println("1. Add Beer");
            System.out.println("2. List all beers");
            System.out.println("3. Add Order");
            System.out.println("4. List all orders");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> addBeer();
                case 2 -> listBeers();
                //case 1 -> addOrder();
                //case 2 -> listOrders();
            }
        }
    }


    private void addBeer(){
        Quantities quantities = Quantities.BELGIAN;
        Containers containers = Containers.CAN;
        System.out.println("Enter beer's name: ");
        String name = scanner.next();
        scanner.nextLine();
        System.out.println("Enter beer's alcohol: ");
        double abv = scanner.nextDouble();
        System.out.println("Enter beer's plato: ");
        int plato = scanner.nextInt();
        System.out.println("Enter beer's style: ");
        String style = scanner.next();
        scanner.nextLine();
        System.out.println("Enter beer's stock: ");
        int stock = scanner.nextInt();
        System.out.println("Enter beer's brewery: ");
        String brewery = scanner.next();
        scanner.nextLine();
        beerService.addBeer(name, abv, plato, style, quantities, stock, containers, brewery );
    }

    private void listBeers(){
        beerService.getAllBeers().forEach(System.out::println);
    }

}
