package roylee.sample.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class SpringBootSecurityApplication {

	public static void main(String[] args) {
	    log.info("SpringBootSecurityApplication-main - 시작!!!!");
		SpringApplication.run(SpringBootSecurityApplication.class, args);
	}

}
