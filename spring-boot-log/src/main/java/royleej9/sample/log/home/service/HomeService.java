package royleej9.sample.log.home.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HomeService {

	private final Logger logger = LoggerFactory.getLogger(HomeService.class);

	public String getHome() {
		logger.error("error getHome");
		logger.warn("warn getHome");
		logger.info("info getHome");
		logger.debug("debug getHome");
		logger.trace("trace getHome");

		return "homeService";
	}
}
