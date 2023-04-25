package com.github.huifer.hardware.sche.entity;

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
 * 公式所需要参数 key:公式中的参数，value：查询条件
 */
@Data
@Entity
@Table(name = "hardware_calc_param_mapping_query")
@FieldNameConstants
@Where(clause="deleted = 0")
public class HardwareCalcParamMappingQueryEntity extends BaseEntity implements Serializable {

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
  @Column(name = "calc_param_id")
  private Long calcParamId;

  /**
   * 设备ID
   */
  @Column(name = "device_id")
  private Long deviceId;

  /**
   * 设备类型ID
   */
  @Column(name = "device_type_id")
  private Long deviceTypeId;


}
