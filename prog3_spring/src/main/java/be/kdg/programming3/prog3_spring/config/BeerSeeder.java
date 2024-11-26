package be.kdg.programming3.prog3_spring.config;

import be.kdg.programming3.prog3_spring.Domain.*;
import be.kdg.programming3.prog3_spring.repository.BeerRepository;
import be.kdg.programming3.prog3_spring.repository.CostumerRepository;
import be.kdg.programming3.prog3_spring.repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Random;
import java.util.stream.Stream;

@Component
public class BeerSeeder implements CommandLineRunner {
    private BeerRepository beerRepository;
    private OrderRepository orderRepository;
    private CostumerRepository costumerRepository;
    public BeerSeeder(BeerRepository beerRepository, OrderRepository orderRepository, CostumerRepository costumerRepository) {
        this.beerRepository = beerRepository;
        this.orderRepository = orderRepository;
        this.costumerRepository = costumerRepository;
    }

    public void run(String... args) throws Exception {
        Random random = new Random();
        //String name, double abv, String style, plato, Quantities quantity, int stock, Containers containers, String brewery
        Stream.generate(()->new Beer("beer"+ random.nextInt(100), Math.round(random.nextDouble(20) * 100.0) / 100.0,random.nextInt(100), "IPA",
                        Quantities.values()[random.nextInt(Quantities.values().length)], random.nextInt(300),
                Containers.values()[random.nextInt(Containers.values().length)], "Omnipollo"+random.nextInt(100), Math.round(random.nextDouble(15) * 100.0) / 100.0, "/images/beer.jpg"))
                .limit(10)
                .forEach(beerRepository::createBeer);

        Stream.generate(()-> new Costumer("Lola"+random.nextInt(100), "KdG", "Kerkstraat 15, 2060", "lola"+random.nextInt(100)+"@gmail.com", "+32546789"+random.nextInt(100), "/images/person.png"))
                .limit(10)
                .forEach(costumerRepository::createCostumer);

        Stream.generate(()->new Order(LocalDate.now(),"comment"+random.nextInt(100), costumerRepository.getCostumerById(random.nextInt(costumerRepository.getSize())), generateRandomBeers(), "/images/orders.jpg"))
                .limit(5).forEach(orderRepository::createOrder);
    }

    public HashMap<Beer, Integer> generateRandomBeers() {
        Random random = new Random();
        HashMap<Beer, Integer> beers = new HashMap<>();
        for (int i = 0; i < random.nextInt(10)+1;) {
            int id=random.nextInt(beerRepository.getSize());
            Beer beer = beerRepository.readBeer(id);
            if (beerRepository.getStock(id)>0){
                beers.put(beer, beerRepository.getStock(id));
                i++;
            }
        }
        return beers;
    }


}
