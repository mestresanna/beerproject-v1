package be.kdg.programming3.prog3_spring.config;
import be.kdg.programming3.prog3_spring.presentation.HttpSession.PageVisitInterceptor;
import be.kdg.programming3.prog3_spring.presentation.converter.StringToBeerConverter;
import be.kdg.programming3.prog3_spring.presentation.converter.StringToCustomerConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final StringToBeerConverter stringToBeerConverter;
    private final StringToCustomerConverter stringToCustomerConverter;
    private final PageVisitInterceptor pageVisitInterceptor;


    public WebConfig(StringToBeerConverter stringToBeerConverter, StringToCustomerConverter stringToCustomerConverter, PageVisitInterceptor pageVisitInterceptor) {
        this.stringToBeerConverter = stringToBeerConverter;
        this.stringToCustomerConverter = stringToCustomerConverter;
        this.pageVisitInterceptor = pageVisitInterceptor;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToBeerConverter);
        registry.addConverter(stringToCustomerConverter);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/customers").setViewName("customers");
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(pageVisitInterceptor).addPathPatterns("/**");
    }
}
