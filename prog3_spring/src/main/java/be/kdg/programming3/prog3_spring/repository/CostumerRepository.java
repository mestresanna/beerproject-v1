package be.kdg.programming3.prog3_spring.repository;

import be.kdg.programming3.prog3_spring.Domain.Costumer;

import java.util.List;

public interface CostumerRepository {
    Costumer createCostumer(Costumer costumer);

    Costumer getCostumerById(int idCostumer);

    List<Costumer> getAllCostumers();
}
