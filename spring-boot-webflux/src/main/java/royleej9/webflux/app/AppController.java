package royleej9.webflux.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AppController {

	@GetMapping(value = "/")
	public String indexView() {
		return "index";
	}

	@GetMapping(value = "/main")
	public String mainView() {
		return "main";
	}

}
