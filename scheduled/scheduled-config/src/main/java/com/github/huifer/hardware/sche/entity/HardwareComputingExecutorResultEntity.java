package com.github.huifer.hardware.sche.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.Where;

/**
 * 硬件计算执行器执行结果
 */

@Data
@Entity
@Table(name = "hardware_computing_executor_result")
@FieldNameConstants
@Where(clause="deleted = 0")
public class HardwareComputingExecutorResultEntity extends  BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * id
   */
  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * 执行时间（注意不是程序计算的执行时间，而是cron表达式的执行时间）
   */
  @Column(name = "execute_tme")
  private BigDecimal executeTme;

  /**
   * 计算规则唯一标识
   */
  @Column(name = "operation_rule_id")
  private Long operationRuleId;

  @Column(name = "step")
  private String step;



  /**
   * 执行结果
   */
  @Column(name = "result")
  private BigDecimal result;

}
