package be.kdg.programming3.prog3_spring.utils;

import be.kdg.programming3.prog3_spring.Domain.Beer;
import be.kdg.programming3.prog3_spring.exceptions.OrderHasNoBeersException;

import java.util.List;

public class OrderUtils {
   public static void checkOrderBeers(List<Beer> beers, int orderId){
       if (beers == null || beers.isEmpty()) { throw new OrderHasNoBeersException("Order " + orderId + " has no beers");}
   }

  public static void checkQuantityBeer(int quantity, int updatedStock){
       if (quantity <= 0) throw new OrderHasNoBeersException("Beer quantity " + quantity + " has to be greater than 0");
       if (updatedStock < 0) throw new OrderHasNoBeersException("Beer stock cannot be negative");
  }
}
