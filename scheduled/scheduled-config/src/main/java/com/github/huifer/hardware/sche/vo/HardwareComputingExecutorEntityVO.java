package com.github.huifer.hardware.sche.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.Data;


@Data
@Schema(description = "保存 硬件计算执行器")
public class HardwareComputingExecutorEntityVO implements Serializable {

  private static final long serialVersionUID = 1L;





  /**
   * 定时任务执行表达式
   */
  @Schema(description = "定时任务执行表达式")
  private String cron;


  /**
   * 计算规则唯一标识
   */
  @Schema(description = "计算规则唯一标识")
  private Long operationRuleId;


}
