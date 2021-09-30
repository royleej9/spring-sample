package com.royleej9.sample.web.user;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.royleej9.sample.user.service.UserService;
import com.royleej9.sample.vo.User;

@Controller
@RequestMapping("/userForm")
public class UserFormController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/userView", method = RequestMethod.GET)
	public ModelAndView userView() {
		List<User> users = userService.getUsers(null);

		return new ModelAndView("/user/userFormView", "users", users);
	}

	@RequestMapping(value = "/insertUser", method = RequestMethod.POST)
	public String insertUserForm(User user) {
		user.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		userService.insertUser(user);
		return "/user/userFormView";
	}

	@ResponseBody
	@RequestMapping(value = "/updateUserName", method = RequestMethod.POST)
	public String updateUserName(@RequestBody User user) {
		userService.updateUserName(user);
		return "/user/listView";
	}

}
