package be.kdg.programming3.prog3_spring.presentation;

import be.kdg.programming3.prog3_spring.Domain.Costumer;
import be.kdg.programming3.prog3_spring.Domain.Order;
import be.kdg.programming3.prog3_spring.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final Logger logger= LoggerFactory.getLogger(OrderController.class);
    private OrderService orderService;

    public OrderController( OrderService orderServiceImp) {
        this.orderService = orderServiceImp;
    }

    @GetMapping
    public String getOrderView(Model model) {
        logger.debug("List of orders");
        List<Order> orders= orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "orders";
    }

    @GetMapping("/add")
    public String getAddBeer(Model model) {
        return "addOrders";
    }
    //String contact, String companyName, String address, String email, String phone
    @PostMapping("/add")
    public String processAddBeer() {
        return "redirect:/orders";
    }

}
