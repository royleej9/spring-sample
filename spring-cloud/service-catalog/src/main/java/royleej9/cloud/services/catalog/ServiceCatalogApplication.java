package royleej9.cloud.services.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ServiceCatalogApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceCatalogApplication.class, args);
	}
}
