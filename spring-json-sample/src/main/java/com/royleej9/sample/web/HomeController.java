package com.royleej9.sample.web;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.royleej9.sample.vo.User;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "home";
	}
	
	@RequestMapping(value = "/ajax/testGet", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> testGet(@RequestParam("id") String id, @RequestParam("pwd") String pwd) {
		logger.info("testGet - id: {} pwd: {}", id, pwd);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("Request", "GET");
		result.put("id", id);
		result.put("pwd", pwd);
		return result;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/ajax/testPostStr", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> testPostStr(@RequestBody String paramStr) throws Exception {
		logger.info("testPostStr - param: {}", paramStr.toString());
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> paramMap = mapper.readValue(paramStr, HashMap.class);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("Request", "POST - String");
		result.put("id", paramMap.get("id"));
		result.put("pwd", paramMap.get("pwd"));

		return result;
	}

	@RequestMapping(value = "/ajax/testPostMap", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> testPostMap(@RequestBody Map<String, Object> param) {
		logger.info("testPostMap - param: {}", param.toString());

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("Request", "POST - Map");
		result.put("id", param.get("id"));
		result.put("pwd", param.get("pwd"));

		return result;
	}

	@RequestMapping(value = "/ajax/testPostVO", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> testPostVO(@RequestBody User user) {
		logger.info("testPostVO - param: {}", user.toString());

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("Request", "POST-User VO");
		result.put("id", user.getId());
		result.put("pwd", user.getPwd());

		return result;
	}
}
