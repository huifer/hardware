package com.github.huifer.hardware.sche.entity;

/**
 * 过滤规则
 **/

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
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

  /**
   * 判断 min <= source <= max
   **/
  public static boolean between(BigDecimal source, BigDecimal max, BigDecimal min) {
    return (source.compareTo(max) < 1) &&
        (source.compareTo(min) > -1);
  }

  public boolean ignore(BigDecimal source) {
    if (ranges != null) {
      List<Boolean> booleans = new ArrayList<>();

      for (BigRange range : ranges) {
        booleans.add(between(source, range.getMax(), range.getMin()));
      }
      return !booleans.contains(true);
    } else {
      return false;
    }
    // 如果true不在里面则表示数据需要忽略
  }

}
