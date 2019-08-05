package com.beiming.mybatis.bean.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Table(name = "user_role_relation")
public class UserRoleRelation {
	
	@Id
	private Integer id;
	
	private Integer sysUserId;
	
	private Integer sysRoleId;

}
