package be.kdg.programming3.prog3_spring.presentation;

import be.kdg.programming3.prog3_spring.Domain.Beer;
import be.kdg.programming3.prog3_spring.Domain.Customer;
import be.kdg.programming3.prog3_spring.Domain.Order;
import be.kdg.programming3.prog3_spring.presentation.viewModels.BeerStockEntry;
import be.kdg.programming3.prog3_spring.presentation.viewModels.BeerViewModel;
import be.kdg.programming3.prog3_spring.presentation.viewModels.OrderViewModel;
import be.kdg.programming3.prog3_spring.service.BeerService;
import be.kdg.programming3.prog3_spring.service.CustomerService;
import be.kdg.programming3.prog3_spring.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final Logger logger= LoggerFactory.getLogger(OrderController.class);
    private OrderService orderService;
    private CustomerService customerService;
    private BeerService beerService;

    public OrderController( OrderService orderServiceImp, CustomerService customerServiceImp, BeerService beerServiceImp) {
        this.orderService = orderServiceImp;
        this.customerService = customerServiceImp;
        this.beerService = beerServiceImp;
    }

    @GetMapping
    public String getOrderView(Model model) {
        logger.debug("List of orders");
        List<Order> orders= orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "orders";
    }

    @GetMapping("/add")
    public String getAddOrder(Model model) {
        logger.debug("Getting order add page");
        OrderViewModel orderViewModel = new OrderViewModel();
        orderViewModel.getBeersList().add(new BeerStockEntry()); // Afegir una entrada buida inicial
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("beers", beerService.getAllBeers());
        model.addAttribute("order", orderViewModel);
        return "/add/addOrders";
    }

    @PostMapping("/add")
    public String processAddOrder(@ModelAttribute("orderViewModel") OrderViewModel orderViewModel, Model model) {
        logger.debug("Add order: " + orderViewModel);
        orderViewModel.populateBeersMap();
        Customer customer = customerService.getCustomer(orderViewModel.getCustomer());
        orderViewModel.setImageUrl("/images/shopping-cart.png");
        Order newOrder = new Order(orderViewModel.getComments(), customer, orderViewModel.getBeers(), orderViewModel.getImageUrl());
        orderService.createOrder(newOrder);
        return "redirect:/orders";
    }

    @GetMapping("/detailOrder")
    public String viewBeer(@RequestParam("idOrder") Integer idOrder, Model model) {
        Order order = orderService.getOrder(idOrder);
        logger.info("View order: " + order);
        model.addAttribute("order", order);
        return "/detail/detailOrder";
    }
}
