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
 * 公式中的静态参数
 */

@Data
@Entity
@Table(name = "hardware_static_calc_param")
@FieldNameConstants
@Where(clause="deleted = 0")
public class HardwareStaticCalcParamEntity extends  BaseEntity implements Serializable {

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
  @Column(name = "calc_params_id")
  private Long calcParamsId;

  /**
   * 公式中的静态参数
   */
  @Column(name = "static_value")
  private BigDecimal staticValue;



}
