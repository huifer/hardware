package com.github.huifer.hardware.sche.entity;

/**
 * 过滤规则
 **/

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FilterEntity implements Serializable {


  /**
   * 过滤器取值范围
   **/
  private List<BigRange> ranges;


  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @ToString
  public static class BigRange {

    private BigDecimal max;
    private BigDecimal min;
  }

}
