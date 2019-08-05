package com.beiming.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.beiming.mybatis.bean.entity.User;
import com.beiming.mybatis.bean.requestdto.UserRequestDTO;
import com.beiming.mybatis.bean.responsedto.UserResponseDTO;
import com.beiming.mybatis.mapper.base.MyMapper;

@Mapper
public interface UserMapper extends MyMapper<User>{
	
	List<UserResponseDTO> getUserInfo(UserRequestDTO userDTO);
	
	List<UserResponseDTO> getUserAllInfo(UserRequestDTO userDTO);

}
