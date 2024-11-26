package be.kdg.programming3.prog3_spring.service;

import be.kdg.programming3.prog3_spring.Domain.Costumer;

import java.util.List;

public interface CostumerService {
    void addCostumer(String contact, String companyName, String address, String email, String phone, String urlImg);

    Costumer getCostumer(int idCostumer);

    List<Costumer> getAllCostumers();
}
