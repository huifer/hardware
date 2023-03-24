package com.github.huifer.hardware.information.entity;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 硬件信号实体
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class HardwareSignalEntity {

  /**
   * 系统型号标识
   **/
  private String sysKey;
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
