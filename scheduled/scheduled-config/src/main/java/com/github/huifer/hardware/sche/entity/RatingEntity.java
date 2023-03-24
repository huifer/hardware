package com.github.huifer.hardware.sche.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.Id;

/**
 * 评分规则
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@EqualsAndHashCode
@ToString
public class RatingEntity implements Serializable {

  @Id
  private String id;

  private List<BigRange> bigRanges;


  private String name;

  public void validateBigRanges() {
    if (bigRanges == null || bigRanges.isEmpty()) {
      throw new IllegalArgumentException("BigRanges must not be empty");
    }

    // 按照 min 升序排序
    bigRanges.sort(Comparator.comparing(BigRange::getMin));

    // 检查相邻元素是否有包含关系
    for (int i = 0; i < bigRanges.size() - 1; i++) {
      BigRange curr = bigRanges.get(i);
      BigRange next = bigRanges.get(i + 1);

      if (curr.getMax().compareTo(next.getMin()) >= 0) {
        throw new IllegalArgumentException("BigRanges contains overlapping elements");
      }
    }
  }

  /**
   * 获取数字对应的评分
   **/
  public String ratingName(BigDecimal bigDecimal) {
    for (BigRange bigRange : bigRanges) {
      if (bigDecimal.compareTo(bigRange.getMin()) >= 0
          && bigDecimal.compareTo(bigRange.getMax()) <= 0) {
        return bigRange.getName();
      }
    }
    return null;
  }

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @FieldNameConstants
  @EqualsAndHashCode
  @ToString
  public static class BigRange {

    private BigDecimal max;
    private BigDecimal min;
    private String name;

    public void validate() {
      if (max != null && min != null && max.compareTo(min) <= 0) {
        throw new IllegalArgumentException("max should be greater than min");
      }
    }
  }

}
