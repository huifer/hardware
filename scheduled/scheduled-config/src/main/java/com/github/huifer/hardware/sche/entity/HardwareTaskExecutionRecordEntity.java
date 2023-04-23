package com.github.huifer.hardware.sche.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.Where;

/**
 * 定时任务执行记录表
 */
@Data
@Entity
@Table(name = "hardware_task_execution_record")
@FieldNameConstants
@Where(clause="deleted = 0")
public class HardwareTaskExecutionRecordEntity extends  BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 唯一标识符
   */
  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * 任务开始时间
   */
  @Column(name = "start_time", nullable = false)
  private Date startTime;

  /**
   * 任务结束时间
   */
  @Column(name = "end_time")
  private Date endTime;

  /**
   * 任务执行状态，0-成功，1-失败
   */
  @Column(name = "status", nullable = false)
  private Integer status;

  /**
   * 错误信息
   */
  @Column(name = "error_message")
  private String errorMessage;

  /**
   * 执行参数
   */
  @Column(name = "execution_parameters")
  private String executionParameters;

  /**
   * 执行cron
   */
  @Column(name = "execution_cron")
  private String executionCron;

  /**
   * 任务id
   */
  @Column(name = "computing_execution_id")
  private Long computingExecutionId;

  /**
   * 执行时间
   */
  @Column(name = "execution_time")
  private Date executionTime;

}
