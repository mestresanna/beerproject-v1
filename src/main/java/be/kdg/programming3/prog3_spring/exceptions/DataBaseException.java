package be.kdg.programming3.prog3_spring.exceptions;

import java.sql.SQLException;

public class DataBaseException extends RuntimeException {

  public DataBaseException(String message, SQLException cause) {
    super(message, cause);
  }

  public DataBaseException(String message) {
        super(message);
    }
}
