package com.beiming.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.beiming.mybatis.bean.User;
import com.beiming.mybatis.bean.requestdto.UserRequestDTO;
import com.beiming.mybatis.bean.responsedto.UserResponseDTO;
import com.beiming.mybatis.mapper.base.MyMapper;

@Mapper
public interface UserMapper extends MyMapper<User>{
	
	UserResponseDTO getUserInfo(UserRequestDTO userDTO);

}
