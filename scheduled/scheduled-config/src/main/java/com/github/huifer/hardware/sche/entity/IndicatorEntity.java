package com.github.huifer.hardware.sche.entity;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

/**
 * 指标实体
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@EqualsAndHashCode
@ToString
public class IndicatorEntity {

  /**
   * 展示用名称
   **/
  private String showName;
  /**
   * 左侧值
   **/
  private BigDecimal min;
  /**
   * 右侧值
   **/
  private BigDecimal max;
}
