package royleej9.sample.log.home;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import royleej9.sample.log.home.service.HomeService;

@RequestMapping("/home")
@RestController
public class HomeController {

	private final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private HomeService homeService;

	@GetMapping
	public String home() {
		String result = homeService.getHome();
		logger.error("error home-{}", result);
		logger.warn("warn home-{}", result);
		logger.info("info home-{}", result);
		logger.debug("debug home-{}", result);
		logger.trace("trace home-{}", result);
		return "home";
	}
	
	@GetMapping(value="/hello")
	public String hello() {
		String result = homeService.getHome();
		logger.error("error hello-{}", result);
		logger.warn("warn hello-{}", result);
		logger.info("info hello-{}", result);
		logger.debug("debug hello-{}", result);
		logger.trace("trace hello-{}", result);
		return "hello";
	}
}
