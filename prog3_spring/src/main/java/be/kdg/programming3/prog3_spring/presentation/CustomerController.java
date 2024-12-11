package be.kdg.programming3.prog3_spring.presentation;

import be.kdg.programming3.prog3_spring.Domain.*;
import be.kdg.programming3.prog3_spring.presentation.viewModels.CustomerViewModel;
import be.kdg.programming3.prog3_spring.service.CustomerService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController{
    private final Logger logger= LoggerFactory.getLogger(CustomerController.class);

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping
    public String getCustomerView(Model model) {
        logger.debug("List of orders");
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "customers";
    }


    @GetMapping("/add")
    public String getAddBeer(Model model) {
        model.addAttribute("customerViewModel", new CustomerViewModel());
        return "/add/addCustomer";
    }

    //String contact, String companyName, String address, String email, String phone
    @PostMapping("/add")
    public String processAddBeer(@Valid @ModelAttribute CustomerViewModel customerViewModel, BindingResult errors, Model model) {
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> {
                logger.error(error.toString());
            });
            return "/add/addCustomer";
        }
        logger.debug("Recieve data for a new beer:" + customerViewModel);
        customerViewModel.setImageUrl("/images/person.png");
        customerService.addCustomer(customerViewModel.getContact(), customerViewModel.getCompanyName(), customerViewModel.getAddress(), customerViewModel.getEmail(), customerViewModel.getPhone(), customerViewModel.getImageUrl());
        return "redirect:/customer";
    }

    @GetMapping("/detailCustomer")
    public String viewBeer(@RequestParam("idCus") Integer idCus, Model model) {
        Customer customer = customerService.getCustomer(idCus);
        logger.info("View customer: " + customer);
        model.addAttribute("customer", customer);
        return "/detail/detailCustomer";
    }
}
