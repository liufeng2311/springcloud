package com.beiming.mybatis.bean.responsedto;

import java.util.Date;
import java.util.List;

import com.beiming.mybatis.bean.base.BaseObject;
import com.beiming.mybatis.bean.entity.RoleAuthRelation;
import com.beiming.mybatis.bean.entity.UserRoleRelation;

import lombok.Data;

@Data
public class UserResponseDTO extends BaseObject{

	private Integer id;
	
	private String username;
	
	private String password;
	
	private Integer version;
	
	private Date updatetime;
	
	private UserRoleRelation relation;
	
	private List<RoleAuthRelation> roleRelation;
}
