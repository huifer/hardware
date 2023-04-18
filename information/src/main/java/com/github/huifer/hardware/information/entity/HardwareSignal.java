package com.github.huifer.hardware.information.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;

import lombok.Data;
import org.hibernate.annotations.Where;

/**
 * 硬件信号标识符表
 */
@Data
@Entity
@Table(name = "hardware_signal")
@Where(clause="deleted = 0 ")
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
  private Float minValue;

  /**
   * 信号最大值
   */
  @Column(name = "max_value")
  private Float maxValue;

  /**
   * 默认告警小区间
   */
  @Column(name = "default_warn_low")
  private Float defaultWarnLow;

  /**
   * 默认告警大区间
   */
  @Column(name = "default_warn_high")
  private Float defaultWarnHigh;



}
