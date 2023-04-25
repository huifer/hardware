package com.github.huifer.hardware.information.vo;


import com.github.huifer.hardware.common.base.PageAndSortRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.Data;

@Data
@Schema(description = "设备信号值查询")
public class HardwareSignalQueryVO extends PageAndSortRequest implements Serializable {

  private static final long serialVersionUID = 1L;


  /**
   * 信号名称
   */
  @Schema(description = "信号名称")
  private String name;


  /**
   * 信号最小值
   */
  @Schema(description = "信号最小值")
  private Float minValue;


  /**
   * 信号最大值
   */
  @Schema(description = "信号最大值")
  private Float maxValue;


  /**
   * 默认告警小区间
   */
  @Schema(description = "默认告警小区间")
  private Float defaultWarnLow;


  /**
   * 默认告警大区间
   */
  @Schema(description = "默认告警大区间")
  private Float defaultWarnHigh;



}
