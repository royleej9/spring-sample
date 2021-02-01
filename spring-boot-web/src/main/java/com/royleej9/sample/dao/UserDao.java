package com.royleej9.sample.dao;

import java.util.List;

import com.royleej9.sample.vo.User;

public interface UserDao {

	int insertUser(User user);

//	List<Map<String, Object>> getUsersMap();

	List<User> getUsers(User user);

	int updateUserName(User user);
	
	int deleteUser(String id);
}