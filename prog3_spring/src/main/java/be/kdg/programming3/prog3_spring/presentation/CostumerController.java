package be.kdg.programming3.prog3_spring.presentation;

import be.kdg.programming3.prog3_spring.Domain.*;
import be.kdg.programming3.prog3_spring.service.CostumerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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


    @GetMapping("/add")
    public String getAddBeer(Model model) {
        return "/add/addCostumer";
    }
    //String contact, String companyName, String address, String email, String phone
    @PostMapping("/add")
    public String processAddBeer(Costumer costumer) {
        logger.debug("Recieve data for a new beer:" + costumer);
        costumerService.addCostumer(costumer.getContact(), costumer.getCompanyName(), costumer.getAddress(),costumer.getEmail(), costumer.getPhone(), costumer.getImageUrl());
        return "redirect:/costumer";
    }

    @GetMapping("/detailCustomer")
    public String viewBeer(@RequestParam("idCus") Integer idCus, Model model) {
        Costumer customer = costumerService.getCostumer(idCus);
        logger.info("View customer: " + customer);
        model.addAttribute("customer", customer);
        return "/detail/detailCustomer";
    }
}
