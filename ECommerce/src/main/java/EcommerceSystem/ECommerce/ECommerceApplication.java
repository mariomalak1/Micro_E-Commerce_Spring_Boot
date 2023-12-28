package EcommerceSystem.ECommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class ECommerceApplication {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ECommerceApplication.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", 7000));
		app.run(args);
	}
}
