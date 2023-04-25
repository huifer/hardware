package com.github.huifer.hardware.information.entity;

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
 * 硬件类型信号关联表
 */
@Data
@Entity
@Table(name = "hardware_signal_mapping")
@FieldNameConstants
@Where(clause="deleted = 0 ")
public class HardwareSignalMapping extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 硬件信号映射关系表主键
   */
  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * 硬件设备ID
   */
  @Column(name = "device_id")
  private Long deviceId;

  /**
   * 硬件输出信号key
   */
  @Column(name = "signal_key")
  private String signalKey;

  /**
   * 系统指定信号名称
   */
  @Column(name = "system_signal_name")
  private String systemSignalName;

}
