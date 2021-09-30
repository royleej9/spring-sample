package royleej9.cloud.services.catalog;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/catalogs/cb")
public class CBController {

	@Autowired
	private CBService cbService;

	// Reactive 스타일이 아닌 경우
	@GetMapping("/timeout/non-reactive/{delay}/{faultPercent}")
	public CompletableFuture<String> timeoutNonReactive(@PathVariable int delay, @PathVariable int faultPercent) {
		return cbService.timeoutNonReactive(delay, faultPercent);
	}

	@GetMapping("/timeout/reactive/{delay}/{faultPercent}")
	public Mono<String> timeoutReactive(@PathVariable int delay, @PathVariable int faultPercent) {
		return cbService.timeoutReactive(delay, faultPercent);
	}
}
