package be.kdg.programming3.prog3_spring.presentation.export;
import be.kdg.programming3.prog3_spring.presentation.converter.LocalDateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class JSONConfiguration {
    @Bean
    public Gson gson() {
        GsonBuilder b = new GsonBuilder();
        b.registerTypeAdapter(LocalDate.class, new LocalDateAdapter().nullSafe());
        return b.setPrettyPrinting().create();
    }
}