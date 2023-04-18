package com.github.huifer.hardware.information.vo;


import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class HardwareSignalQueryVO implements Serializable {

  private static final long serialVersionUID = 1L;


  /**
   * 硬件信号标识符表主键
   */
  private Long id;


  /**
   * 信号名称
   */
  private String name;


  /**
   * 信号最小值
   */
  private Float minValue;


  /**
   * 信号最大值
   */
  private Float maxValue;


  /**
   * 默认告警小区间
   */
  private Float defaultWarnLow;


  /**
   * 默认告警大区间
   */
  private Float defaultWarnHigh;



}
