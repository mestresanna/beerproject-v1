package be.kdg.programming3.prog3_spring.presentation;

import be.kdg.programming3.prog3_spring.Domain.*;
import be.kdg.programming3.prog3_spring.presentation.viewModels.BeerStockEntry;
import be.kdg.programming3.prog3_spring.presentation.viewModels.OrderViewModel;
import be.kdg.programming3.prog3_spring.service.BeerService;
import be.kdg.programming3.prog3_spring.service.CustomerService;
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

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final Logger logger= LoggerFactory.getLogger(OrderController.class);
    private final OrderService orderService;
    private final CustomerService customerService;
    private final BeerService beerService;

    public OrderController( OrderService orderServiceImp, CustomerService customerServiceImp, BeerService beerServiceImp) {
        this.orderService = orderServiceImp;
        this.customerService = customerServiceImp;
        this.beerService = beerServiceImp;
    }

    @GetMapping
    public String getOrderView(Model model, HttpSession session) {
        createSessionParameters(session);

        logger.debug("List of orders");
        List<Order> orders= orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "orders";
    }

    @GetMapping("/add")
    public String getAddOrder(Model model, HttpSession session) {
        createSessionParameters(session);

        logger.debug("Getting order add page");
        OrderViewModel orderViewModel = new OrderViewModel();
        orderViewModel.getBeersList().add(new BeerStockEntry());

        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("beers", beerService.getAllBeers());
        model.addAttribute("order", orderViewModel);
        return "/add/addOrders";
    }

    @PostMapping("/add")
    public String processAddOrder(@Valid @ModelAttribute("orderViewModel") OrderViewModel orderViewModel, BindingResult errors, Model model) {
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> {
                logger.error(error.toString());
            });
            model.addAttribute("customers", customerService.getAllCustomers());
            model.addAttribute("beers", beerService.getAllBeers());
            return "/add/addOrders";
        }
        logger.debug("Add order: " + orderViewModel);
        orderViewModel.populateBeersMap();
        Customer customer = customerService.getCustomer(orderViewModel.getCustomer());
        orderViewModel.setImageUrl("/images/shopping-cart.png");
        Order newOrder = new Order(orderViewModel.getComments(), customer, orderViewModel.getBeers(), orderViewModel.getImageUrl());
        orderService.createOrder(newOrder);
        return "redirect:/orders";
    }

    @GetMapping("/detailOrder")
    public String viewBeer(@RequestParam("idOrder") Integer idOrder, Model model, HttpSession session) {
        createSessionParameters(session); // not in current use, we use Session Scope

        Order order = orderService.getOrder(idOrder);
        logger.info("View order: " + order);
        model.addAttribute("order", order);

        return "/detail/detailOrder";
    }


    @GetMapping("/delete/beer")
    public String deleteBeer(@RequestParam("idOrder") Integer idOrder, @RequestParam("id") Integer id, HttpSession session, Model model) {
        createSessionParameters(session); // not in current use, we use Session Scope

        orderService.deleteBeer(idOrder, id);

        return "redirect:/orders/detailOrder?idOrder=" + idOrder;
    }


    @GetMapping("/delete")
    public String deleteOrder(@RequestParam("id") int id) {
        orderService.delete(id);
        return "redirect:/orders";
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
