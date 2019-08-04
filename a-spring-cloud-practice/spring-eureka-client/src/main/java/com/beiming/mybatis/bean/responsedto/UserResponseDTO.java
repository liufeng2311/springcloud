package com.beiming.mybatis.bean.responsedto;

import java.util.Date;

import com.beiming.mybatis.bean.RoleAuthRelation;

import lombok.Data;

@Data
public class UserResponseDTO {

	private Integer id;
	
	private String username;
	
	private String password;
	
	private Integer version;
	
	private Date updatetime;
	
	private RoleAuthRelation relation;
}
