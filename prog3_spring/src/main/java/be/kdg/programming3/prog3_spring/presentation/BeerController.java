package be.kdg.programming3.prog3_spring.presentation;

import be.kdg.programming3.prog3_spring.Domain.Beer;
import be.kdg.programming3.prog3_spring.Domain.Containers;
import be.kdg.programming3.prog3_spring.Domain.Quantities;
import be.kdg.programming3.prog3_spring.service.BeerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/beer")
public class BeerController {

    private final Logger logger= LoggerFactory.getLogger(BeerController.class);
    private BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping
    public String getBeerView(Model model) {
        logger.debug("List of beers");
        List<Beer> beers= beerService.getAllBeers();
        model.addAttribute("beers", beers);
        return "beers";
    }

    @GetMapping("/add")
    public String getAddBeer(Model model) {
        return "addBeer";
    }

    //String name, double abv, String style, Quantities quantity, int stock, Containers containers, String brewery, int price
    @PostMapping("/add")
    public String processAddBeer(Model model, Beer beer) {
        logger.debug("Recieve data for a new beer:" + beer.getName());
        List<Quantities> beerQuantities = new ArrayList<Quantities>(Arrays.asList(Quantities.values()));
        model.addAttribute("beerQuantities", beerQuantities);
        List<Containers> beerContainers = new ArrayList<Containers>(Arrays.asList(Containers.values()));
        model.addAttribute("beerContainers", beerContainers);
        beerService.addBeer(beer.getName(), beer.getAbv(), beer.getPlatoBeer(),  beer.getStyle(), beer.getQuantity(), beer.getStock(), beer.getContainers(), beer.getBrewery(), beer.getPrice());
        return "redirect:/beers";
    }



}
