package com.beiming.test;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author fenghouzhi
 * @date 2019-08-20 - 10:35
 * @description:
 */
@Data
@AllArgsConstructor
public class GenerateParamKeyModel implements Serializable {

  private String cStr;
  private String cKey;

}
