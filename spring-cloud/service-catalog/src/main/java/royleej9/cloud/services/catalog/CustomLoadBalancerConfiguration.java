package royleej9.cloud.services.catalog;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.RandomLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

public class CustomLoadBalancerConfiguration {

	// round robin -> random 방식으로 변경
	@Bean
	ReactorLoadBalancer<ServiceInstance> randomLoadBalancer(Environment environment,
			LoadBalancerClientFactory loadBalancerClientFactory) {
		String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
		System.out.println("CustomLoadBalancerConfiguration : " + name);
		return new RandomLoadBalancer(
				loadBalancerClientFactory.getLazyProvider(name, ServiceInstanceListSupplier.class), name);
	}

//	@Bean
//	public ServiceInstanceListSupplier discoveryClientServiceInstanceListSupplier(
//			ConfigurableApplicationContext context) {
//		System.out.println("ServiceInstanceListSupplier");
//		return ServiceInstanceListSupplier.builder().withDiscoveryClient().withSameInstancePreference().build(context);
//	}
}
