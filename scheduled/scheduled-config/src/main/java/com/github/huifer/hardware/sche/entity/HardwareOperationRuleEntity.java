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
 * 硬件计算规则
 */

@Data
@Entity
@Table(name = "hardware_operation_rule")
@FieldNameConstants
@Where(clause="deleted = 0")
public class HardwareOperationRuleEntity  extends  BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * id
   */
  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * 公式名称
   */
  @Column(name = "name")
  private String name;

  /**
   * 公式别名
   */
  @Column(name = "alias")
  private String alias;

  /**
   * 公式
   */
  @Column(name = "calc")
  private String calc;

  /**
   * 是否为阶段运算值，如果是则需要等最后运算
   */
  @Column(name = "step")
  private Boolean step;

  /**
   * 计算优先级，和step有关
   */
  @Column(name = "order")
  private Integer order;


}
