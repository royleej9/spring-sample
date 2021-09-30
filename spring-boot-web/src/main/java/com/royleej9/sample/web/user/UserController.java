package com.royleej9.sample.web.user;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.royleej9.sample.user.service.UserService;
import com.royleej9.sample.vo.User;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/userFormView", method = RequestMethod.GET)
	public ModelAndView userFormView() {
		List<User> users = userService.getUsers(null);
		
		return new ModelAndView("/user/userFormView", "users", users);
	}
	
	@RequestMapping(value="/v", method = RequestMethod.GET)
	public ModelAndView listView(Locale locale, Model model) {
		List<User> users = userService.getUsers(null);
		
		return new ModelAndView("/user/listView", "users", users);
	}

	@RequestMapping(value="/insertUserForm", method = RequestMethod.POST)
	public String insertUserForm(User user) {
		userService.insertUser(user);
		return "/user/userFormView";
	}
	
	@ResponseBody
	@RequestMapping(value="/insertUser", method = RequestMethod.POST)
	public String insertUser(@RequestBody User user) {
		userService.insertUser(user);
		return "/user/listView";
	}
	
	@ResponseBody
	@RequestMapping(value="/updateUserName", method = RequestMethod.POST)
	public String updateUserName(@RequestBody User user) {
		userService.updateUserName(user);
		return "/user/listView";
	}
	
}
