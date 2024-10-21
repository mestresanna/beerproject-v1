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
import java.util.EnumSet;
import java.util.List;

@Controller
@RequestMapping("/beers")
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
        model.addAttribute("quantity", Quantities.values());
        model.addAttribute("container", Containers.values());
        return "addBeer";
    }
    @PostMapping("/add")
    public String processAddBeer(Beer beer) {
        logger.debug("Recieve data for a new beer:" + beer);
        beerService.addBeer(beer.getName(), beer.getAbv(), beer.getPlatoBeer(),  beer.getStyle(), beer.getQuantity(), beer.getStock(), beer.getContainers(), beer.getBrewery(), beer.getPrice());
        return "redirect:/beers";
    }


}
