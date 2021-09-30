package royleej9.cloud.services.catalog;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.bulkhead.annotation.Bulkhead.Type;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import reactor.core.publisher.Mono;

@Service
public class CBService {

	private static final String CB_SERVICE = "cbService";

	@Autowired
	private RestTemplate restTemplate;

	@CircuitBreaker(name = CB_SERVICE, fallbackMethod = "fallback")
	public String testCircuitBreaker(String customerId) {
		List a = null;
		a.get(10);
		return restTemplate.getForObject("http://fall-api/customers/" + customerId, String.class);
	}

	@TimeLimiter(name = CB_SERVICE)
	@CircuitBreaker(name = CB_SERVICE, fallbackMethod = "fallbackLocalServiceTimeLimiter")
	public Mono<String> timeoutLocal(int delay, int faultPercent) {
		sleep(delay * 1000);
		return Mono.just("Test-timeoutLocal");
	}

	@TimeLimiter(name = CB_SERVICE)
	@Bulkhead(name = CB_SERVICE, type = Type.THREADPOOL)
	@CircuitBreaker(name = CB_SERVICE, fallbackMethod = "fallbackRemoteServiceTimeLimiter")
	public CompletableFuture<String> timeoutRemoteService(int delay, int faultPercent) {
		String url = "http://customer-api/customers/retry/" + delay + "/" + faultPercent;
		String result = restTemplate.getForObject(url, String.class);

		return CompletableFuture.completedFuture(result);
	}

	// @CircuitBreaker 실패시 호출되는 메소드
	@SuppressWarnings("unused")
	private String fallback(String customerId, IllegalStateException ex) {
		System.out.println("IllegalStateException" + System.currentTimeMillis());
		return "Failed to call api - IllegalStateException" + System.currentTimeMillis();
	}

	// @CircuitBreaker 실패시 호출되는 메소드
	@SuppressWarnings("unused")
	private String fallback(String customerId, Exception ex) {
		System.out.println("Exception" + System.currentTimeMillis());
		return "Failed to call api - Exception" + System.currentTimeMillis();
	}

	private String fallback(String customerId, RuntimeException e) {
		return "time-out" + System.currentTimeMillis();
	}

	private Mono<String> fallbackLocalServiceTimeLimiter(int delay, int faultPercent, Exception ex) {
		System.out.println("fallbackLocalServiceTimeLimiter" + System.currentTimeMillis());
		return Mono.just("fallbackLocalServiceTimeLimiter" + System.currentTimeMillis());
	}

	private CompletableFuture<String> fallbackRemoteServiceTimeLimiter(int delay, int faultPercent, Exception ex) {
		System.out.println("CompletableFuture-fallbackRemoteServiceTimeLimiter" + System.currentTimeMillis());
		return CompletableFuture
				.completedFuture("CompletableFuture-fallbackRemoteServiceTimeLimiter" + System.currentTimeMillis());
	}

	private void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
