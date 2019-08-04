package com.beiming.mybatis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beiming.mybatis.bean.User;
import com.beiming.mybatis.bean.requestdto.UserRequestDTO;
import com.beiming.mybatis.bean.responsedto.UserResponseDTO;
import com.beiming.mybatis.mapper.UserMapper;

@RestController
@RequestMapping("/mybatis")
public class MybatisController {
	
	@Autowired
	private UserMapper userMapper;
	
	@RequestMapping("/cloums")
	public void cloums() {
		UserRequestDTO user = new UserRequestDTO();
		user.setUsername("liufeng");
		UserResponseDTO userInfo = userMapper.getUserInfo(user);
		System.out.println(userInfo);
	}

}
