package be.kdg.programming3.prog3_spring.config;

import be.kdg.programming3.prog3_spring.Domain.*;
import be.kdg.programming3.prog3_spring.repository.BeerRepository;
import be.kdg.programming3.prog3_spring.repository.CustomerRepository;
import be.kdg.programming3.prog3_spring.repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;

import java.util.HashMap;
import java.util.Random;
import java.util.stream.Stream;

//@Component
public class BeerSeeder implements CommandLineRunner {
    private BeerRepository beerRepository;
    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;
    public BeerSeeder(BeerRepository beerRepository, OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.beerRepository = beerRepository;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    public void run(String... args) throws Exception {
        Random random = new Random();
        //String name, double abv, String style, plato, Quantities quantity, int stock, Containers containers, String brewery
        Stream.generate(()->new Beer("beer"+ random.nextInt(100), Math.round(random.nextDouble(20) * 100.0) / 100.0,random.nextInt(100), "IPA",
                        Quantities.values()[random.nextInt(Quantities.values().length)], random.nextInt(300),
                Containers.values()[random.nextInt(Containers.values().length)], "Omnipollo"+random.nextInt(100), Math.round(random.nextDouble(15) * 100.0) / 100.0, "/images/beers.png"))
                .limit(10)
                .forEach(beer ->{
                        switch(beer.getContainers()){
                            case CAN ->  beer.setImageUrl("/images/beer-can.png");
                            case BOTTLE ->  beer.setImageUrl("/images/beer-bottle.png");
                            case KEG ->  beer.setImageUrl("/images/beer-keg.png");
                        }
                        beerRepository.save(beer);});

        Stream.generate(()-> new Customer("Lola"+random.nextInt(100), "KdG", "Kerkstraat 15, 2060", "lola"+random.nextInt(100)+"@gmail.com", "+32546789"+random.nextInt(100), "/images/person.png"))
                .limit(10)
                .forEach(customerRepository::save);


        Stream.generate(()->new Order("comment"+random.nextInt(100), customerRepository.findById(random.nextInt(customerRepository.getSize())), generateRandomBeers(), "/images/shopping-cart.png"))
                .limit(5).forEach(orderRepository::save);
    }

    public HashMap<Beer, Integer> generateRandomBeers() {
        Random random = new Random();
        HashMap<Beer, Integer> beers = new HashMap<>();
        for (int i = 0; i < random.nextInt(10)+1;) {
            int id=random.nextInt(beerRepository.getSize());
            Beer beer = beerRepository.findById(id);
            if (beerRepository.getStock(id)>0){
                beers.put(beer, beerRepository.getStock(id));
                i++;
            }
        }
        return beers;
    }


}
