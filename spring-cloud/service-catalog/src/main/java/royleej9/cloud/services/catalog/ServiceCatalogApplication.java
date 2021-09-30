package royleej9.cloud.services.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

// https://cloud.spring.io/spring-cloud-commons/multi/multi__spring_cloud_commons_common_abstractions.html
// 선언하지 않아도 DiscoveryClient 인테페이스 구현체를 찾아서 자동으로 등록하지만 autoRegister=false를 통해 수동으로 비활성화를 할수도 있음
@EnableDiscoveryClient

@EnableEurekaClient
@SpringBootApplication
public class ServiceCatalogApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceCatalogApplication.class, args);
	}
}
