package com.github.huifer.hardware.information.entity;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 硬件型号实体
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HardwareSignalEntity {

  /**
   * 硬件供应商提供的信号键
   **/
  private String key;
  /**
   * 信号名称
   **/
  private String name;
  /**
   * 信号最小值
   **/
  private BigDecimal min;
  /**
   * 信号最大值
   **/
  private BigDecimal max;


  /**
   * 信号默认告警小区间
   **/
  private BigDecimal defaultWaringMin;
  /**
   * 信号默认告警大区间
   **/
  private BigDecimal defaultWaringMax;


}
