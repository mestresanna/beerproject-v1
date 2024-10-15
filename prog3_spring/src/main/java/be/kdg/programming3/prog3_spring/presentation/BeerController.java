package be.kdg.programming3.prog3_spring.presentation;

import be.kdg.programming3.prog3_spring.Domain.Beer;
import be.kdg.programming3.prog3_spring.service.BeerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

/*
   public DogController(DogService dogService) {
        this.dogService=dogService;
    }

    @GetMapping
    public String getDogsVIew(Model model) {
        logger.debug("Dog List");
        List<Dog> dogs= dogService.getDogs();
        model.addAttribute("dogs", dogs);
        return "dogs";
    }

    @GetMapping("/add")
    public String getAddDogForm(){
        return "adddog";
    }

    @PostMapping("/add")
    public String processAddDog(Model model, Dog dog) {
        logger.debug("Recieve form data for a new dog:" + dog.getName());
        List<DogType> dogTypes = new ArrayList<DogType>(Arrays.asList(DogType.values()));
        model.addAttribute("dogTypes", dogTypes);
        dogService.addDog(dog.getName(), dog.getDogType());
        return "redirect:/dogs";
    }
 */

}
