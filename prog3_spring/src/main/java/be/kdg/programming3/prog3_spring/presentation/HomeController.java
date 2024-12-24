package be.kdg.programming3.prog3_spring.presentation;

import be.kdg.programming3.prog3_spring.Domain.Beer;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class HomeController {
    private final Logger logger= LoggerFactory.getLogger(HomeController.class);

    @GetMapping
    public String getBeerView(Model model, HttpSession session) {
        Map<String, List<LocalDateTime>> sessionMap = (Map<String, List<LocalDateTime>>) session.getAttribute("sessionMap");
        if (sessionMap == null) {
            logger.info("sessionMap is null, creating a new session");
            sessionMap = new HashMap<>();
            session.setAttribute("sessionMap", sessionMap);
        }
        sessionMap.computeIfAbsent( ServletUriComponentsBuilder.fromCurrentRequest().toUriString() , k -> new ArrayList<>()).add(LocalDateTime.now());
        logger.debug("sessionMap: " + sessionMap);

        logger.debug("Create Home");
        return "home";
    }
}
