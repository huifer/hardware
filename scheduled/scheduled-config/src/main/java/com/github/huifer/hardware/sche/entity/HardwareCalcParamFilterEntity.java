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
 * 公式参数过滤条件
 */

@Data
@Entity
@Table(name = "hardware_calc_param_filter")
@FieldNameConstants
@Where(clause="deleted = 0")
public class HardwareCalcParamFilterEntity extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * id
   */
  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * 公式参数id
   */
  @Column(name = "calc_param_id")
  private Long calcParamId;

  /**
   * 范围最大值
   */
  @Column(name = "ranges_max")
  private BigDecimal rangesMax;

  /**
   * 范围最大值
   */
  @Column(name = "ranges_min")
  private BigDecimal rangesMin;

  /**
   * 默认左等于 0 左等于 1 右等于
   */
  @Column(name = "lefteq_righteq_state")
  private Boolean lefteqRighteqState;


}
