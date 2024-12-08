package be.kdg.programming3.prog3_spring.config;
import be.kdg.programming3.prog3_spring.presentation.converter.StringToBeerConverter;
import be.kdg.programming3.prog3_spring.presentation.converter.StringToCustomerConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final StringToBeerConverter stringToBeerConverter;
    private final StringToCustomerConverter stringToCustomerConverter;

    public WebConfig(StringToBeerConverter stringToBeerConverter, StringToCustomerConverter stringToCustomerConverter) {
        this.stringToBeerConverter = stringToBeerConverter;
        this.stringToCustomerConverter = stringToCustomerConverter;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToBeerConverter);
        registry.addConverter(stringToCustomerConverter);
    }
}
