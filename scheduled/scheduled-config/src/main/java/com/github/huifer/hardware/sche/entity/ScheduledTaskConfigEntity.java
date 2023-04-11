package com.github.huifer.hardware.sche.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

/**
 * 任务执行计划配置
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@EqualsAndHashCode
@ToString
public class ScheduledTaskConfigEntity {

  /**
   *
   **/
  private String id;
  /**
   * 任务id
   **/
  private String taskId;
  /**
   * 执行时间
   **/
  private String cron;
  

}
