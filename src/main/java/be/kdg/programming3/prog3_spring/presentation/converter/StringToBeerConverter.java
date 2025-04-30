package be.kdg.programming3.prog3_spring.presentation.converter;

import be.kdg.programming3.prog3_spring.Domain.Beer;
import be.kdg.programming3.prog3_spring.service.BeerService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToBeerConverter implements Converter<String, Beer> {
    private final BeerService beerService;

    public StringToBeerConverter(BeerService beerService) {
        this.beerService = beerService;
    }

    @Override
    public Beer convert(String idBeer) {
        try {
            int beerId = Integer.parseInt(idBeer);
            return beerService.getBeerById(beerId);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
