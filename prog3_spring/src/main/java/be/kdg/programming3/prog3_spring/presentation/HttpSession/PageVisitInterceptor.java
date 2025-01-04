package be.kdg.programming3.prog3_spring.presentation.HttpSession;

import be.kdg.programming3.prog3_spring.Domain.HttpSession.SessionHistory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class PageVisitInterceptor implements HandlerInterceptor {
    private Logger logger= LoggerFactory.getLogger(PageVisitInterceptor.class);
    private SessionHistory sessionHistory;


    public PageVisitInterceptor(SessionHistory sessionHistory) {
        this.sessionHistory = sessionHistory;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (sessionHistory == null) {
            logger.error("sessionHistory is null");
            return true; // Continue without checking the session
        }
        String uri = request.getRequestURI();
        sessionHistory.addPageVisit(uri);
        return true;
    }
}
