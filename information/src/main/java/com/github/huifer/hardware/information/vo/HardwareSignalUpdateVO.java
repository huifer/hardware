package com.github.huifer.hardware.information.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class HardwareSignalUpdateVO   implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 信号名称
   */
  @NotNull(message = "名称不能为空")
  @Schema(description = "信号名称")
  private String name;

  /**
   * 信号名称
   */
  @NotNull(message = "信号编号不能为空")
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

  /**
   * 是否参与计算
   * <p>true：启用；false：禁用
   */
  @Schema(description = "是否参与计算<p>true：启用；false：禁用")
  private Boolean state;

}
