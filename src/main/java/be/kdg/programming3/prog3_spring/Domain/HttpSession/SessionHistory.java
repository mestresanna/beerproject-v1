package be.kdg.programming3.prog3_spring.Domain.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.time.LocalDateTime;
import java.util.*;

@Component
@SessionScope
public class SessionHistory {
    private Map<String, ArrayList<LocalDateTime>> pageVisits = new TreeMap<>();

    public void addPageVisit(String pageName) {
        pageVisits.computeIfAbsent(pageName,  k -> new ArrayList<>()).add(LocalDateTime.now());
    }

    public  Map<String, ArrayList<LocalDateTime>> getPageVisits() {
        return pageVisits;
    }
}
