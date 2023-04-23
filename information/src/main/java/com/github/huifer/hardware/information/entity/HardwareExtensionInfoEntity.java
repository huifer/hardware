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
 * 硬件扩展信息表
 */
@Data
@Entity
@Table(name = "hardware_extension_info")
@Where(clause="deleted = 0 ")
public class HardwareExtensionInfoEntity extends  BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 硬件扩展信息表主键
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
   * 扩展信息
   */
  @Column(name = "ext_info")
  private String extInfo;



}
