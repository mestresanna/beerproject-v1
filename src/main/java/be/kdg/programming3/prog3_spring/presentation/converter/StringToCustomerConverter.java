package be.kdg.programming3.prog3_spring.presentation.converter;

import be.kdg.programming3.prog3_spring.Domain.Customer;
import be.kdg.programming3.prog3_spring.service.CustomerService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToCustomerConverter implements Converter<String, Customer> {
    private final CustomerService customerService;
    public StringToCustomerConverter(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public Customer convert(String source) {
        try {
            int idCustomer = Integer.parseInt(source);
            return customerService.getCustomer(idCustomer);
        } catch (NumberFormatException e) {
            return null;
        }
    }


}
