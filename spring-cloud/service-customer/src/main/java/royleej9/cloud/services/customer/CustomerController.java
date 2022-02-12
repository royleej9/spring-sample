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
        return String.format("[customer-delay][%s][delay = %s at %s ]", getServerInfo(), delay,
                System.currentTimeMillis());
    }

    private String getServerInfo() {
        return serverProperties.getAddress().toString() + ":" + serverProperties.getPort();
    }
}
