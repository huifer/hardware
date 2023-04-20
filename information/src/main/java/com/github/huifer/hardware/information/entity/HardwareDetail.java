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
 * 设备详情表
 */

@Data
@Entity
@Table(name = "hardware_detail")
@FieldNameConstants
@Where(clause="deleted = 0 and state = 0 ")
public class HardwareDetail extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 设备详情表主键
   */
  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * 设备名称
   */
  @Column(name = "name")
  private String name;

  /**
   * 硬件唯一标识
   */
  @Column(name = "device_num")
  private String deviceNum;

  /**
   * 设备地址
   */
  @Column(name = "address")
  private String address;

  /**
   * 设备所在经度
   */
  @Column(name = "latitude")
  private Float latitude;

  /**
   * 设备所在纬度
   */
  @Column(name = "longitude")
  private Float longitude;

  /**
   * 状态
   */
  @Column(name = "state")
  private Boolean state;

}
