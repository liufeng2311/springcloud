package com.beiming.mybatis.bean.entity;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "user")
public class User {

	@Id
	private Integer id;
	
	private String username;
	
	private String password;
	
	private Integer version;
	
	private Date updatetime;
}
