package com.github.huifer.hardware.sche.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.Where;

/**
 * 公式所需要参数查询时间
 */

@Data
@Entity
@Table(name = "hardware_calc_param_mapping_query_time")
@FieldNameConstants
@Where(clause="deleted = 0")
public class HardwareCalcParamMappingQueryTimeEntity extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * id
   */
  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * 所需参数
   */
  @Column(name = "calc_param_id")
  private Long calcParamId;

  /**
   * 查询时间
   */
  @Column(name = "start_time")
  private LocalDateTime startTime;

  /**
   * 查询结束时间
   */
  @Column(name = "end_time")
  private LocalDateTime endTime;


}
