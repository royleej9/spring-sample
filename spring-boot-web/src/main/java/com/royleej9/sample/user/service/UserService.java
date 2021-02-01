package com.royleej9.sample.user.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.royleej9.sample.vo.User;

@Transactional
public interface UserService {

	public int insertUser(User user);

	public int insertUserTransaction(User user);

	@Transactional(readOnly = true)
	public List<User> getUsers(User user);

	public int updateUserName(User user);

	public int deleteUser(String id);
}
