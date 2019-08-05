package com.beiming.mybatis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beiming.mybatis.bean.requestdto.UserRequestDTO;
import com.beiming.mybatis.bean.responsedto.UserResponseDTO;
import com.beiming.mybatis.mapper.UserMapper;

@RestController
@RequestMapping("/mybatis")
public class MybatisController {
	
	@Autowired
	private UserMapper userMapper;
	
	@RequestMapping("/cloums")
	public Object cloums() {
		UserRequestDTO user = new UserRequestDTO();
		user.setUsername("liufeng");
		List<UserResponseDTO> userInfo = userMapper.getUserInfo(user);
		return userInfo;
	}
	
	@RequestMapping("/all")
	public Object cloumsAll() {
		UserRequestDTO user = new UserRequestDTO();
		user.setUsername("liufeng");
		List<UserResponseDTO> userInfo = userMapper.getUserAllInfo(user);
		return userInfo;
	}
}
