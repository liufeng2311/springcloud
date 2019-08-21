package com.beiming.test;

import java.io.Serializable;
import lombok.Data;

/**
 * @author fenghouzhi
 * @date 2019-08-20 - 09:23
 * @description:
 */
@Data
public class GenerateRequestParamModel implements Serializable {

  /**
   * 法官姓名
   */
  private String name;

  /**
   * 法官手机号码
   */
  private String phone;

  /**
   * 法院代码
   */
  private String fydm;

  /**
   * 身份证号码，法官编码，办公系统 oaid 均可
   */
  private String identifyId;

}