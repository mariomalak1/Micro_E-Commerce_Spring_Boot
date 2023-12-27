package EcommerceSystem.ECommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ECommerceApplication {
	public static void main(String[] args) {

		SpringApplication.run(ECommerceApplication.class, args);

		SpringApplication app = new SpringApplication(ECommerceApplication.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", 8080));
		app.run(args);

	}
}
