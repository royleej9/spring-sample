package com.royleej9;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.royleej9.sample.user.service.UserServiceImpl;

@SpringBootApplication
@MapperScan(value = {"com.royleej9"})
public class SpringBootWebApplication implements CommandLineRunner{
	
	
	@Autowired
	private UserServiceImpl userServiceImpl;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userServiceImpl.getUsers(null);
		// TODO Auto-generated method stub
		
	}
}
