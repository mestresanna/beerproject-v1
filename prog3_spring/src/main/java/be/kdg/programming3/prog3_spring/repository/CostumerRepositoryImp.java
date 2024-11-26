package be.kdg.programming3.prog3_spring.repository;

import be.kdg.programming3.prog3_spring.Domain.Costumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


public class CostumerRepositoryImp implements CostumerRepository {
    private static List<Costumer> costumers = new ArrayList<>();
    private Logger logger = LoggerFactory.getLogger(CostumerRepositoryImp.class);


    @Override
    public Costumer createCostumer(Costumer costumer) {
        costumers.add(costumer);
        costumer.setIdCostumer(costumers.size()-1);
        logger.info("Creating new beer: {}, with id: {}", costumer, costumer.getIdCostumer());
        return costumer;
    }

    @Override
    public Costumer getCostumerById(int idCostumer) {
        logger.debug("Creating new beer: {}, with id: {}", costumers.get(idCostumer), idCostumer);
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
