package be.kdg.programming3.prog3_spring.repository;

import be.kdg.programming3.prog3_spring.Domain.Costumer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


public class CostumerRepositoryImp implements CostumerRepository {
    private static List<Costumer> costumers = new ArrayList<>();

    @Override
    public Costumer createCostumer(Costumer costumer) {
        costumers.add(costumer);
        costumer.setIdCostumer(costumers.size());
        return costumer;
    }

    @Override
    public Costumer getCostumerById(int idCostumer) {
        return costumers.get(idCostumer);
    }

    @Override
    public List<Costumer> getAllCostumers() {
        return costumers;
    }

    public int getSize(){
        return costumers.size();
    }
}
