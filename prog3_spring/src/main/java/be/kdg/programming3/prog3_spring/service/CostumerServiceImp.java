package be.kdg.programming3.prog3_spring.service;

import be.kdg.programming3.prog3_spring.Domain.Costumer;
import be.kdg.programming3.prog3_spring.repository.CostumerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;


public class CostumerServiceImp implements CostumerService {
    private Logger logger = LoggerFactory.getLogger(CostumerServiceImp.class);
    private CostumerRepository costumerRepository;
    public CostumerServiceImp(CostumerRepository costumerRepository) {
        this.costumerRepository = costumerRepository;
    }

    @Override
    public void addCostumer(String contact, String companyName, String address, String email, String phone) {
        logger.info("Adding costumer " + contact);
        Costumer costumer = new Costumer(contact, companyName, address, email, phone);
        costumerRepository.createCostumer(costumer);
    }

    @Override
    public Costumer getCostumer(int idCostumer) {
        return costumerRepository.getCostumerById(idCostumer);
    }

    @Override
    public List<Costumer> getAllCostumers() {
        return costumerRepository.getAllCostumers();
    }
}
