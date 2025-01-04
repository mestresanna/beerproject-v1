package be.kdg.programming3.prog3_spring.presentation.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import be.kdg.programming3.prog3_spring.Domain.HttpSession.SessionHistory;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sessions")
public class SessionHistoryController {
    private Logger logger= LoggerFactory.getLogger(SessionHistoryController.class);

    private SessionHistory sessionHistory;

    public SessionHistoryController(SessionHistory sessionHistory) {
        this.sessionHistory = sessionHistory;
    }

    @GetMapping
     public String getSessionHistory(Model model, HttpSession session) {
        /* Using Session Parameters
        Map<String, List<LocalDateTime>> sessionMap = (Map<String, List<LocalDateTime>>) session.getAttribute("sessionMap");
        if (sessionMap == null) {
            logger.debug("No history session found, creating an empty history session");
            sessionMap = new HashMap<>();
            session.setAttribute("sessionMap", sessionMap);
        }


        logger.info("sessionMap in getSessionHistory: " + sessionMap);
        model.addAttribute("sessionMap", sessionMap);*/

        model.addAttribute("sessionMap", sessionHistory.getPageVisits());
        String ipAddr = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest().getRemoteAddr();
        model.addAttribute("ipAddr", ipAddr);
        return "session/history";
    }

}
