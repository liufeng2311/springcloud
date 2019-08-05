package com.beiming.mybatis.bean.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Table(name = "role_auth_relation")
public class RoleAuthRelation {

	@Id
	private  Integer id;
	
	private Integer roleId;
	
	private Integer permissionId;
}
