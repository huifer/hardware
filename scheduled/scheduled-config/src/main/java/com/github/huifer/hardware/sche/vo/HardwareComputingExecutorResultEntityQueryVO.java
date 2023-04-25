package com.github.huifer.hardware.sche.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

@Data
@Schema(description = "自定义查询 硬件计算执行器执行结果")
public class HardwareComputingExecutorResultEntityQueryVO implements Serializable {

  private static final long serialVersionUID = 1L;




  /**
   * 执行时间（注意不是程序计算的执行时间，而是cron表达式的执行时间）
   */
  @Schema(description = "执行时间（注意不是程序计算的执行时间，而是cron表达式的执行时间）")
  private BigDecimal executeTme;


  /**
   * 计算规则唯一标识
   */
  @Schema(description = "计算规则唯一标识")
  private Long operationRuleId;

  @Schema(description = "计算等级")
  private String step;




  /**
   * 执行结果
   */
  @Schema(description = "执行结果")
  private BigDecimal result;

}
