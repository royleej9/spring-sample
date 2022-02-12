package royleej9.sample.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@RestController
public class DefaultController {

	private final Logger logger = LoggerFactory.getLogger(DefaultController.class);

	@GetMapping
	public String defaultController() {
		long time = System.currentTimeMillis();

		logger.error("error defaultController-{}", time);
		logger.warn("warn defaultController-{}", time);
		logger.info("info defaultController-{}", time);
		logger.debug("debug defaultController-{}", time);
		logger.trace("trace defaultController-{}", time);

		return "test";
	}
}
