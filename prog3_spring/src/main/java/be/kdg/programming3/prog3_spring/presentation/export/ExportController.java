package be.kdg.programming3.prog3_spring.presentation.export;

import be.kdg.programming3.prog3_spring.Domain.Beer;
import be.kdg.programming3.prog3_spring.Domain.Order;

import be.kdg.programming3.prog3_spring.presentation.export.dto.BeerDTO;
import be.kdg.programming3.prog3_spring.presentation.export.dto.OrderDTO;
import be.kdg.programming3.prog3_spring.service.BeerService;
import be.kdg.programming3.prog3_spring.service.OrderService;
import com.google.gson.Gson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/export")
public class ExportController {
    private Logger logger = LoggerFactory.getLogger(ExportController.class);

    private BeerService beerService;
    private OrderService orderService;
    private Gson gson;

    public ExportController(BeerService beerService, OrderService orderService, Gson gson) {
        this.beerService = beerService;
        this.orderService = orderService;
        this.gson = gson;
    }

    @GetMapping("/beers.json")
    public ResponseEntity<String> exportBeers() {
        logger.info("Downloading all beers.json");
        List<Beer> beers = beerService.getAllBeers();
        List<BeerDTO> beerDTOs = beers.stream().map(BeerDTO::new).toList();

        String jsonString = gson.toJson(beerDTOs);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=beers.json");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return ResponseEntity.ok()
                .headers(headers)
                .body(jsonString);
    }

    @GetMapping("/orders.json")
    public ResponseEntity<String>  exportOrders() {
        logger.info("Downloading all orders.json");
        List<Order> orders = orderService.getAllOrders();
        List<OrderDTO> orderDTOS = orders.stream().map(OrderDTO::new).toList();

        String json = gson.toJson(orderDTOS);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=orders.json");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        return ResponseEntity.ok()
                .headers(headers)
                .body(json);
    }
}
