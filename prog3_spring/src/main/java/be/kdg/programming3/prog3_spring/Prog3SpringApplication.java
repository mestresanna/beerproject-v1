package be.kdg.programming3.prog3_spring;

import be.kdg.programming3.prog3_spring.presentation.Menu;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Prog3SpringApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(Prog3SpringApplication.class, args);
		context.getBean(Menu.class).show();
		context.close();
	}

}
