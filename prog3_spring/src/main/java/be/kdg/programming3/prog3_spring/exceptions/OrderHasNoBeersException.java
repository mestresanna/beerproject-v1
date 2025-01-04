package be.kdg.programming3.prog3_spring.exceptions;


import java.sql.SQLException;

public class OrderHasNoBeersException extends RuntimeException {
    public OrderHasNoBeersException(String message) {
        super(message);
    }

    public OrderHasNoBeersException(String message, SQLException cause) {
        super(message, cause);
    }

}
