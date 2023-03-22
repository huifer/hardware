package com.github.huifer.hardware.sche.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
  }

}
