package royleej9.cloud.services.catalog;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/cb")
public class CBController {

	@Autowired
	private CBService cbService;


	@GetMapping("/test-ok/{customerId}")
	public String testOK(@PathVariable int customerId) {
		return cbService.testOK(customerId);
	}
	
	// timeout-Reactive 스타일이 아닌 경우
	@GetMapping("/testError/{customerId}")
	public String testError(@PathVariable int customerId) {
		return cbService.testError(customerId);
	}

//	// timeout-Reactive 스타일이 아닌 경우
	@GetMapping("/timeout/non-reactive/{delay}/{faultPercent}")
	public CompletableFuture<String> timeoutNonReactive(@PathVariable int delay, @PathVariable int faultPercent) {
		int a = Runtime.getRuntime().availableProcessors() - 1;
		System.out.println(a);
		
		return cbService.timeoutNonReactive(delay, faultPercent);
	}
//
//	// timeout-Reactive 스타일이 경우
//	@GetMapping("/timeout/reactive/{delay}/{faultPercent}")
//	public Mono<String> timeoutReactive(@PathVariable int delay, @PathVariable int faultPercent) {
//		return cbService.timeoutReactive(delay, faultPercent);
//	}
}
