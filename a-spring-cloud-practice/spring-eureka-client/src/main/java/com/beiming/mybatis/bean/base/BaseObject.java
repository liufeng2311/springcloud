package com.beiming.mybatis.bean.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = "handler")  //该注解解决mybatis开启延迟加载后返回前端数据序列化异常问题
public class BaseObject {

}
