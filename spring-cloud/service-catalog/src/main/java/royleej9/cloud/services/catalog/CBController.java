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

	@GetMapping("/timeout/timeoutLocal/{delay}/{faultPercent}")
	public Mono<String> timeoutLocal(@PathVariable int delay, @PathVariable int faultPercent) {
		return cbService.timeoutLocal(delay, faultPercent);
	}

	@GetMapping("/timeout/remote-service/{delay}/{faultPercent}")
	public CompletableFuture<String> timeoutRemoteService(@PathVariable int delay, @PathVariable int faultPercent) {
		return cbService.timeoutRemoteService(delay, faultPercent);
	}
}
