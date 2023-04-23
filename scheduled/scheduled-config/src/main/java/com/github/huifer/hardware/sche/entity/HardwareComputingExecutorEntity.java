package com.github.huifer.hardware.sche.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.Where;

/**
 * 硬件计算执行器
 */
@Data
@Entity
@Table(name = "hardware_computing_executor")
@FieldNameConstants
@Where(clause="deleted = 0")
public class HardwareComputingExecutorEntity extends  BaseEntity  implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * id
   */
  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * 定时任务执行表达式
   */
  @Column(name = "cron")
  private String cron;

  /**
   * 计算规则唯一标识
   */
  @Column(name = "operation_rule_id")
  private Long operationRuleId;


}
