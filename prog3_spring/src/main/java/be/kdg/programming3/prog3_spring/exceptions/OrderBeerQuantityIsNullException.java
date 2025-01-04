package be.kdg.programming3.prog3_spring.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLException;
@ResponseStatus(reason="Beer quantity cannot be less than 1")
public class OrderBeerQuantityIsNullException extends RuntimeException {
    public OrderBeerQuantityIsNullException(String message) {
        super(message);
    }
  public OrderBeerQuantityIsNullException(String message, SQLException cause) {
    super(message, cause);
  }
}
