package be.kdg.programming3.prog3_spring.presentation;

import be.kdg.programming3.prog3_spring.Domain.Costumer;
import be.kdg.programming3.prog3_spring.Domain.Order;
import be.kdg.programming3.prog3_spring.service.CostumerService;
import be.kdg.programming3.prog3_spring.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/costumer")
public class CostumerController {
    private final Logger logger= LoggerFactory.getLogger(CostumerController.class);

    private CostumerService costumerService;

    public CostumerController( CostumerService costumerService) {
        this.costumerService = costumerService;
    }

    @GetMapping
    public String getCostumerView(Model model) {
        logger.debug("List of orders");
        List<Costumer> costumers= costumerService.getAllCostumers();
        model.addAttribute("costumers", costumers);
        return "costumers";
    }


}
