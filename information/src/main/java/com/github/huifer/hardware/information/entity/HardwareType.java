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
public class HardwareType  extends  BaseEntity implements Serializable {

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
   * 是否启用停用
   *  <p>true：启用；false：禁用
   */
  @Column(name = "state")
  private Boolean state;


  @Column(name = "unit")
  private String unit;

}
