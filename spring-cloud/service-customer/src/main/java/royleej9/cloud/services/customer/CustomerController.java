package royleej9.cloud.services.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private ServerProperties serverProperties;

	@GetMapping("/{customerId}")
	public String getCustomerDetail(@PathVariable String customerId) {
		System.out.println("request customerId :" + customerId);
//		return "[customer-service : Customer id = " + customerId + " at " + System.currentTimeMillis() + "]";

		return String.format("[customer][%s][Customer id = %s at %s ]", getServerInfo(), customerId,
				System.currentTimeMillis());
	}
	
	@GetMapping("/delay/{delay}")
	public String getCustomerInfoDelay(@PathVariable int delay) {
		System.out.println("request delay :" + delay);
		sleep(delay * 1000);
		return String.format("[customer-delay][%s][delay = %s at %s ]", getServerInfo(), delay,
				System.currentTimeMillis());
	}

	@GetMapping("/retry/{delay}/{faultPercent}")
	public String retry(@PathVariable int delay, @PathVariable int faultPercent) {
		System.out.println("retry :" + delay + "/" + faultPercent);

		if (delay > 0)
			sleep(delay * 1000);

		return String.format("[customer-timeout][%s][delay id = %s at %s ]", getServerInfo(), delay,
				System.currentTimeMillis());
	}

//	public String testTimeout(int delay, int faultPercent) {
//
////		if (faultPercent < )
//
//		return "timeout-test";
//	}

	private void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String getServerInfo() {
		return serverProperties.getAddress().toString() + ":" + serverProperties.getPort();
	}
}
