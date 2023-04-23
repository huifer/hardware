package com.github.huifer.hardware.sche.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.Data;

@Data
@Schema(description = "自定义查询 硬件计算执行器")
public class HardwareComputingExecutorEntityQueryVO implements Serializable {

  private static final long serialVersionUID = 1L;


  /**
   * id
   */
  @Schema(description = "id")
  private Long id;


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
