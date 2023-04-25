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
 * 公式所需参数
 */

@Data
@Entity
@Table(name = "hardware_calc_params")
@FieldNameConstants
@Where(clause="deleted = 0")
public class HardwareCalcParamsEntity  extends  BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * Id
   */
  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * 公式规则
   */
  @Column(name = "operation_rule_id")
  private Long operationRuleId;

  /**
   * 公式所需要参数
   */
  @Column(name = "param")
  private String param;

  /**
   * 硬件类型
   */
  @Column(name = "hardware_type")
  private String hardwareType;

  /**
   * 信号值
   */
  @Column(name = "signal")
  private String signal;



}
