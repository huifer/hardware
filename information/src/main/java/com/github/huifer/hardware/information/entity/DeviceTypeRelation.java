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
 * 设备类型关联表
 */
@Data
@Entity
@FieldNameConstants
@Table(name = "device_type_relation")
@Where(clause="deleted = 0 ")
public class DeviceTypeRelation extends BaseEntity  implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 设备详情表主键
   */
  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * 设备详情表中的设备ID
   */
  @Column(name = "device_id")
  private Long deviceId;

  /**
   * 硬件类型表中的硬件类型ID
   */
  @Column(name = "type_id")
  private Long typeId;


}
