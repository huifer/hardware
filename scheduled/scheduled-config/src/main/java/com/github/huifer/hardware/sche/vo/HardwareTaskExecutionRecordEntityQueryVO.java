package com.github.huifer.hardware.sche.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Schema(description = "自定义查询 定时任务执行记录表")
public class HardwareTaskExecutionRecordEntityQueryVO implements Serializable {

  private static final long serialVersionUID = 1L;





  /**
   * 任务开始时间
   */
  @Schema(description = "任务开始时间")
  private LocalDateTime startTime;


  /**
   * 任务结束时间
   */
  @Schema(description = "任务结束时间")
  private LocalDateTime endTime;


  /**
   * 任务执行状态，0-成功，1-失败
   */
  @Schema(description = "任务执行状态，0-成功，1-失败")
  private Integer status;


  /**
   * 错误信息
   */
  @Schema(description = "错误信息")
  private String errorMessage;


  /**
   * 执行参数
   */
  @Schema(description = "执行参数")
  private String executionParameters;


  /**
   * 执行cron
   */
  @Schema(description = "执行cron")
  private String executionCron;


  /**
   * 任务id
   */
  @Schema(description = "任务id")
  private Long computingExecutionId;


  /**
   * 执行时间
   */
  @Schema(description = "执行时间")
  private LocalDateTime executionTime;

}
