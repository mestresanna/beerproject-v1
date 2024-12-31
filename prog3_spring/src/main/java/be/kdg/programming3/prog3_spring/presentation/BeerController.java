package be.kdg.programming3.prog3_spring.presentation;

import be.kdg.programming3.prog3_spring.Domain.Beer;
import be.kdg.programming3.prog3_spring.Domain.Containers;
import be.kdg.programming3.prog3_spring.Domain.Order;
import be.kdg.programming3.prog3_spring.Domain.Quantities;
import be.kdg.programming3.prog3_spring.presentation.viewModels.BeerViewModel;
import be.kdg.programming3.prog3_spring.service.BeerService;
import be.kdg.programming3.prog3_spring.service.OrderService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Controller
@RequestMapping("/beers")
public class BeerController{

    private final Logger logger= LoggerFactory.getLogger(BeerController.class);
    private final BeerService beerService;
    private final OrderService orderService;

    public BeerController(BeerService beerService, OrderService orderService) {
        this.beerService = beerService;
        this.orderService = orderService;
    }

    @GetMapping
    public String getBeerView(HttpSession session, Model model) {
        createSessionParameters(session); // not un current use, we use Session Scope
        logger.debug("List of beers");
        List<Beer> beers= beerService.getAllBeers();
        model.addAttribute("beers", beers);
        return "beers";
    }

    @GetMapping("/add")
    public String getAddBeer(Model model, HttpSession session) {
        createSessionParameters(session); // not un current use, we use Session Scope

        model.addAttribute("quantity", Quantities.values());
        model.addAttribute("container", Containers.values());
        model.addAttribute("beerViewModel", new BeerViewModel());
        return "/add/addBeer";
    }



    @PostMapping("/add")
    public String processAddBeer(@Valid  @ModelAttribute("beerViewModel")  BeerViewModel beerViewModel, BindingResult errors, Model model) {
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> {
                logger.error(error.toString());
            });
            model.addAttribute("quantity", Quantities.values());
            model.addAttribute("container", Containers.values());
            return "/add/addBeer";
        }
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
    public String viewBeer(@RequestParam("idBeer") Integer idBeer, Model model, HttpSession session) {
        createSessionParameters(session); // not un current use, we use Session Scope

        Beer beer = beerService.getBeerById(idBeer);
        logger.info("View beer: " + beer);
        model.addAttribute("beer", beer);

        List<Order> order = new ArrayList<>();
        if (beer.getOrders() != null) {
            for (int i = 0; i < beer.getOrders().size(); i++) {
               // order.add(orderService.getOrder(beer.getOrders().get(i)));
            }
        }
        model.addAttribute("orderList", order);

        return "/detail/detailBeer";
    }

    private void createSessionParameters(HttpSession session) {
        // Uncomment to use Session Parameters instead of Session Scope
       /* Map<String, List<LocalDateTime>> sessionMap = (Map<String, List<LocalDateTime>>) session.getAttribute("sessionMap");
        if (sessionMap == null) {
            logger.info("sessionMap is null, creating a new session");
            sessionMap = new HashMap<>();
            session.setAttribute("sessionMap", sessionMap);
        }
        sessionMap.computeIfAbsent( ServletUriComponentsBuilder.fromCurrentRequest().toUriString() ,  k -> new ArrayList<>()).add(LocalDateTime.now());
        logger.debug("sessionMap: " + sessionMap);*/
    }

}
