package be.kdg.programming3.prog3_spring.exceptions;

import be.kdg.programming3.prog3_spring.service.jpa.OrderServiceJPA;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    @ExceptionHandler(DataBaseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleDatabaseException(DataBaseException ex, HttpServletRequest re) {
        logger.error(ex.getMessage(), ex);
        ModelAndView modelAndView = new ModelAndView("error/error-database");
        modelAndView.addObject("errorMessage", ex.getMessage());
        modelAndView.addObject("url", re.getRequestURL());
        return modelAndView;
    }

    @ExceptionHandler({OrderHasNoBeersException.class, OrderBeerQuantityIsNullException.class})
    public ModelAndView handleOrderHasNoBeersException(Exception ex, HttpServletRequest re) {
        logger.error(ex.getMessage(), ex);
        ModelAndView modelAndView = new ModelAndView("error/order-exception");
        modelAndView.addObject("errorMessage", ex.getMessage());
        modelAndView.addObject("url", re.getRequestURL());
        return modelAndView;
    }



    @ExceptionHandler(Exception.class)
    public ModelAndView handleGenericException(Exception ex, HttpServletRequest re) {
        logger.error(ex.getMessage(), ex);
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorMessage", ex.getMessage());
        modelAndView.addObject("url", re.getRequestURL());
        return modelAndView;
    }
}
