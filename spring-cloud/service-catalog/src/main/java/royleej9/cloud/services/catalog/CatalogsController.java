package royleej9.cloud.services.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/catalogs/customerinfo")
public class CatalogsController {

	@Autowired
	private CustomerApiService customerApiService;

	@Autowired
	private ServerProperties serverProperties;

	@GetMapping(path = "/{customerId}")
	public String getCustomerInfo(@PathVariable String customerId) {
		String customerInfo = customerApiService.getCustomerDetail(customerId);
		System.out.println("response customerInfo : " + customerInfo);

		return String.format("[catalogs][%s][Customer id = %s at %s %s ]", getServerInfo(), customerId,
				System.currentTimeMillis(), customerInfo);
	}


	@GetMapping(path = "/raw/{customerId}")
	public String getCustomerInfoRww(@PathVariable String customerId) {
		String customerInfo = customerApiService.getCustomerDetailRaw(customerId);
		System.out.println("response customerInfo : " + customerInfo);
		return String.format("[Customer id = %s at %s %s ]", customerId, System.currentTimeMillis(), customerInfo);
	}

	private String getServerInfo() {
		return serverProperties.getAddress().toString() + ":" + serverProperties.getPort();
	}
}
