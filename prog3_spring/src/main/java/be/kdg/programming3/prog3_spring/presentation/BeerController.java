package be.kdg.programming3.prog3_spring.presentation;

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

    @GetMapping
    public String getBeerView(Model model) {
        logger.debug("Dog List");
        return "beers";
    }


}
