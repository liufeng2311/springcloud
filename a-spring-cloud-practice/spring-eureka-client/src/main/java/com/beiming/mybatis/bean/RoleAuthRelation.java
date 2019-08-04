package com.beiming.mybatis.bean;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "role_auth_relation")
public class RoleAuthRelation {

	@Id
	private  Integer id;
	
	private Integer roleId;
	
	private Integer permissionId;
}
