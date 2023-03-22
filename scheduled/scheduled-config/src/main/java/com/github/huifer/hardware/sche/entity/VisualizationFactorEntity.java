package com.github.huifer.hardware.sche.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
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
 * 可视化系数实体，用于返回前端使用
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@EqualsAndHashCode
@ToString
public class VisualizationFactorEntity implements Serializable {

  @Id
  private String id;
  /**
   * 公式id
   **/
  private String ruleId;
  /**
   * 每个时间所需要的系数
   **/
  private List<TimeFactor> timeFactors;
  /**
   * 评分规则id
   **/
  private String ratingEntityId;


  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @FieldNameConstants
  @EqualsAndHashCode
  @ToString
  public static class TimeFactor {

    private BigDecimal k;

    /**
     * 作用时间
     */
    private LocalDate actionTime;
  }
}
