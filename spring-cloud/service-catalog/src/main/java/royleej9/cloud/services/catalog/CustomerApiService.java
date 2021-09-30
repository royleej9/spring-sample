package royleej9.cloud.services.catalog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerApiService {

	private static final String CUSTOMER_SERVICE = "customerService";

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private DiscoveryClient discoveryClient;

	public String getCustomerDetail(String customerId) {
//		return restTemplate.getForObject("http://localhost:8085/customers/" + customerId, String.class);
		return restTemplate.getForObject("http://customer-api/customers/" + customerId, String.class);
	}

	// DiscoveryClient 직접 사용 할 경우 개발자가 Eureka에 등록된 서비스 리스트를 조회 및 선택 후 URL을 직접 만들어야 함
	public String getCustomerDetailRaw(String customerId) {
		RestTemplate restTemplate = new RestTemplate();
		List<ServiceInstance> instances = discoveryClient.getInstances("customer");
		if (instances.size() == 0)
			return null;

		String serviceUri = String.format("%s/customers/%s", instances.get(0).getUri().toString(), customerId);
		ResponseEntity<String> restExchange = restTemplate.exchange(serviceUri, HttpMethod.GET, null, String.class,
				customerId);
		return restExchange.getBody();
	}

}
