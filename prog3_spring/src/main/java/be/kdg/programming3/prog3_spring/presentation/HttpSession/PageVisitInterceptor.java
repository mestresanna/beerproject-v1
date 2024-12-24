package be.kdg.programming3.prog3_spring.presentation.HttpSession;

import be.kdg.programming3.prog3_spring.Domain.HttpSession.SessionHistory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class PageVisitInterceptor implements HandlerInterceptor {

    @Autowired
    private SessionHistory sessionHistory;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        String uri = request.getRequestURI();
        sessionHistory.addPageVisit(uri);
        return true;
    }
}
