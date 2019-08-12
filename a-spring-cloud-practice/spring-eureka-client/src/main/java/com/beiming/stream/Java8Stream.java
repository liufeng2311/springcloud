package com.beiming.stream;

import java.util.ArrayList;
import java.util.List;

import com.beiming.mybatis.bean.entity.User;

public class Java8Stream {
	
	static List<User> users;
	
	static {
		users = new ArrayList<User>();
		for(int i = 0; i < 10; i++) {
			User user = new User();
			user.setUsername("liufeng" + i);
			user.setPassword("123456");
			users.add(user);
		}
	}


	public static void main(String[] args) {

		users.stream().filter(user -> user.getUsername().equals("liufeng6"))
		.forEach(user -> System.out.println(user.getUsername()));

	}
}
