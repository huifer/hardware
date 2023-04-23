package com.github.huifer.hardware.sche.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.Data;


@Data
@Schema(description = "保存 公式所需参数")
public class HardwareCalcParamsEntityVO implements Serializable {

  private static final long serialVersionUID = 1L;





  /**
   * 公式规则
   */
  @Schema(description = "公式规则")
  private Long operationRuleId;


  /**
   * 公式所需要参数
   */
  @Schema(description = "公式所需要参数")
  private String param;


  /**
   * 硬件类型
   */
  @Schema(description = "硬件类型")
  private String hardwareType;


  /**
   * 信号值
   */
  @Schema(description = "信号值")
  private String signal;





}
