package royleej9.junit.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class GetController {

    
    public GetController() {
        log.info("INIT GetController==================================");
    }
    
	@RequestMapping(value = "/test-get", method = RequestMethod.GET)
	public Map<String, Object> testGet() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", "test123");

		return result;
	}
}
