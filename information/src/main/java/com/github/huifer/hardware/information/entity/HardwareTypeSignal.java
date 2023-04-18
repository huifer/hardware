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
 * 硬件类型信号关联表
 */
@Data
@Entity
@Table(name = "hardware_type_signal")
@Where(clause="deleted = 0 ")
public class HardwareTypeSignal extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 关系主键
   */
  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * 硬件类型表中的硬件类型ID
   */
  @Column(name = "type_id")
  private Long typeId;

  /**
   * 硬件信号标识符表中的硬件信号ID
   */
  @Column(name = "signal_id")
  private Long signalId;


}
