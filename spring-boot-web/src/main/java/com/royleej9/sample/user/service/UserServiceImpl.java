package com.royleej9.sample.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.royleej9.sample.dao.UserDao;
import com.royleej9.sample.vo.User;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userMapper;

	public int insertUser(User user) {
		return userMapper.insertUser(user);
	}
	
	public int insertUserTransaction(User user) {
		// normal
		userMapper.insertUser(user);		
		
		// exception
		user.setId(null);
		userMapper.insertUser(user);
		return 0;
	}
	
	public List<User> getUsers(User user) {
		return userMapper.getUsers(user);
	}

	public int updateUserName(User user) {
		return userMapper.updateUserName(user);
	}

	public int deleteUser(String id) {
		return userMapper.deleteUser(id);
	}
}
