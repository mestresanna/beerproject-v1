package be.kdg.programming3.prog3_spring.presentation.viewModels;

import be.kdg.programming3.prog3_spring.Domain.Beer;
import jakarta.validation.constraints.*;

public class BeerStockEntry {
    @NotNull(message = "not beer")
    private Beer beer;

    @PositiveOrZero
    private int stock;


    public Beer getBeer() {
        return beer;
    }

    public void setBeer(Beer beer) {
        this.beer = beer;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

}
