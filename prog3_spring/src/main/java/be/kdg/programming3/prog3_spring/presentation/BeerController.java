package be.kdg.programming3.prog3_spring.presentation;

import be.kdg.programming3.prog3_spring.Domain.Beer;
import be.kdg.programming3.prog3_spring.Domain.Containers;
import be.kdg.programming3.prog3_spring.Domain.Quantities;
import be.kdg.programming3.prog3_spring.presentation.viewModels.BeerViewModel;
import be.kdg.programming3.prog3_spring.service.BeerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("beerViewModel", new BeerViewModel());

        return "/add/addBeer";

    }
    @PostMapping("/add")
    public String processAddBeer(@ModelAttribute("beerViewModel")  BeerViewModel beerViewModel, Model model) {
        logger.debug("Recieve data for a new beer:" + beerViewModel);
        switch(beerViewModel.getContainers()){
            case CAN ->  beerViewModel.setImageUrl("/images/beer-can.png");
            case BOTTLE ->  beerViewModel.setImageUrl("/images/beer-bottle.png");
            case KEG ->  beerViewModel.setImageUrl("/images/beer-keg.png");
        }

        Beer newBeer = new Beer(beerViewModel.getName(), beerViewModel.getAbv(), beerViewModel.getPlatoBeer(),  beerViewModel.getStyle(), beerViewModel.getQuantity(), beerViewModel.getStock(), beerViewModel.getContainers(), beerViewModel.getBrewery(), beerViewModel.getPrice(), beerViewModel.getImageUrl());
        beerService.addBeer(newBeer);
        return "redirect:/beers";
    }

    @GetMapping("/detailBeer")
    public String viewBeer(@RequestParam("idBeer") Integer idBeer, Model model) {
        Beer beer = beerService.getBeerById(idBeer);
        logger.info("View beer: " + beer);
        model.addAttribute("beer", beer);
        return "/detail/detailBeer";
    }


}
