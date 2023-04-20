package com.github.huifer.hardware.information.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

@Data
@Schema(description = "硬件信号值")
public class HardwareSignalDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 硬件信号标识符表主键
   */
  @Schema(description = "id")
  private Long id;


  /**
   * 信号名称
   */
  @Schema(description = "信号名称")
  private String name;
  /**
   * 信号编号
   */
  @Schema(description = "信号编号")
  private String signalNum;
  /**
   * 信号最小值
   */
  @Schema(description = "信号最小值")
  private BigDecimal minValue;


  /**
   * 信号最大值
   */
  @Schema(description = "信号最大值")
  private BigDecimal maxValue;


  /**
   * 默认告警小区间
   */
  @Schema(description = "默认告警小区间")
  private BigDecimal defaultWarnLow;


  /**
   * 默认告警大区间
   */
  @Schema(description = "默认告警大区间")
  private BigDecimal defaultWarnHigh;



}
