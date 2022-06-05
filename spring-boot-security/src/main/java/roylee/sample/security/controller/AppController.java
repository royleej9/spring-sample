package roylee.sample.security.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AppController {
	
	public AppController() {
		log.info("AppController!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	}

	@GetMapping(value="/")
	public String indexView() {
		return "/login";
	}
	
	@GetMapping(value="/login")
	public String loginView() {
		return "/login";
	}
	
	@PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_ADMIN')")
	@GetMapping(value="/main")
	public String mainView() {
		log.info(SecurityContextHolder.getContext().getAuthentication().toString());
		return "/main";
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping(value="/admin")
	public String adminView() {
		log.info(SecurityContextHolder.getContext().getAuthentication().toString());
		return "/admin";
	}
}
