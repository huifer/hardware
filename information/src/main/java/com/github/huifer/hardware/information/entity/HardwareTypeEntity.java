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
 * 硬件类型表
 */
@Data
@Entity
@Table(name = "hardware_type")
@Where(clause="deleted = 0 and  ")
public class HardwareTypeEntity extends  BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 设备详情表主键
   */
  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * 硬件类型名称
   */
  @Column(name = "name")
  private String name;

  /**
   * 硬件类型编码
   */
  @Column(name = "code")
  private String code;


  /**
   * 硬件类型状态
   */
  @Column(name = "state")
  private Integer state;



}
