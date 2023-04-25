package com.github.huifer.hardware.information.entity;

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
 * 硬件信号标识符表
 */
@Data
@Entity
@Table(name = "hardware_signal")
@FieldNameConstants
@Where(clause="deleted = 0  and state = 0")
public class HardwareSignal extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 硬件信号标识符表主键
   */
  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * 信号名称
   */
  @Column(name = "name")
  private String name;

  /**
   * 信号最小值
   */
  @Column(name = "min_value")
  private BigDecimal minValue;

  /**
   * 信号最大值
   */
  @Column(name = "max_value")
  private BigDecimal maxValue;

  /**
   * 默认告警小区间
   */
  @Column(name = "default_warn_low")
  private BigDecimal defaultWarnLow;

  /**
   * 默认告警大区间
   */
  @Column(name = "default_warn_high")
  private BigDecimal defaultWarnHigh;


  /**
   * 是否启用停用
   *  <p>true：启用；false：禁用
   */
  @Column(name = "state")
  private Boolean state;
  /**
   * 单位
   *  <p>true：启用；false：禁用
   */
  @Column(name = "unit")
  private String unit;
}
