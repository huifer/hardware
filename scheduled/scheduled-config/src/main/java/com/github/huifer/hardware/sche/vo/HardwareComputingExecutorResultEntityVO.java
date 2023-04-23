package com.github.huifer.hardware.sche.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;


@Data
@Schema(description = "保存 硬件计算执行器执行结果")
public class HardwareComputingExecutorResultEntityVO implements Serializable {

  private static final long serialVersionUID = 1L;


  /**
   * id
   */
  @NotNull(message = "id不能为空")(message = "id can not null")
  @Schema(description = "id")
  private Long id;


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
