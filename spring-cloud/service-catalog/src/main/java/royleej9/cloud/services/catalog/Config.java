package royleej9.cloud.services.catalog;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.core.registry.EntryAddedEvent;
import io.github.resilience4j.core.registry.EntryRemovedEvent;
import io.github.resilience4j.core.registry.EntryReplacedEvent;
import io.github.resilience4j.core.registry.RegistryEventConsumer;

@Configuration
@LoadBalancerClient(value = "customer", configuration = CustomLoadBalancerConfiguration.class)
public class Config {
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@LoadBalanced
	@Bean
	WebClient.Builder webClientBuilder() {
		return WebClient.builder();
	}

	@Bean
	public RegistryEventConsumer<CircuitBreaker> myRegistryEventConsumer() {

		return new RegistryEventConsumer<CircuitBreaker>() {
			@Override
			public void onEntryAddedEvent(EntryAddedEvent<CircuitBreaker> entryAddedEvent) {
				entryAddedEvent.getAddedEntry().getEventPublisher()
						.onEvent(event -> {
							System.out.println(event.toString());
						});
			}

			@Override
			public void onEntryRemovedEvent(EntryRemovedEvent<CircuitBreaker> entryRemoveEvent) {

			}

			@Override
			public void onEntryReplacedEvent(EntryReplacedEvent<CircuitBreaker> entryReplacedEvent) {

			}
		};
	}
//
//	@Bean
//	public RegistryEventConsumer<Retry> myRetryRegistryEventConsumer() {
//
//		return new RegistryEventConsumer<Retry>() {
//			@Override
//			public void onEntryAddedEvent(EntryAddedEvent<Retry> entryAddedEvent) {
//				entryAddedEvent.getAddedEntry().getEventPublisher().onEvent(event -> System.out.println(event.toString()));
//			}
//
//			@Override
//			public void onEntryRemovedEvent(EntryRemovedEvent<Retry> entryRemoveEvent) {
//
//			}
//
//			@Override
//			public void onEntryReplacedEvent(EntryReplacedEvent<Retry> entryReplacedEvent) {
//
//			}
//		};
//	}

}
