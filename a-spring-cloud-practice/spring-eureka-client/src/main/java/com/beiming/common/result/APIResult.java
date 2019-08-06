package com.beiming.common.result;

import java.io.Serializable;

import lombok.Data;

@Data
public class APIResult implements Serializable {

  private static final long serialVersionUID = 5855123652125726312L;
 
  private int code;	//状态码
  private Object data; //数据
  private String message; //提示信息

  private APIResult() {

  }

  private APIResult(Object data) {
    this.code = 0;
    this.data = data;
  }

  private APIResult(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public static APIResult success() {
    return new APIResult(null);
  }

  public static APIResult success(Object data) {
    return new APIResult(data);
  }
  
  public static APIResult fail(String message) {
	  return new APIResult(10001, message);
  }
  
  public static APIResult fail(int code, String message) {
	  return new APIResult(code , message);
  }

}
