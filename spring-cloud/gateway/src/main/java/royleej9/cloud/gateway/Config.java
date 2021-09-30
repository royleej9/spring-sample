package royleej9.cloud.gateway;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
	@Bean
	public GlobalFilter customFilter() {
	    return new CustomGlobalFilter();
	}
}
