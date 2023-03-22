package com.github.huifer.hardware.sche.entity;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

/**
 * 指标组实体
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@EqualsAndHashCode
@ToString
public class IndicatorGroupEntity {

  /**
   * 指标组名称
   **/
  private String name;
  /**
   * 指标具体范围
   **/
  private List<IndicatorEntity> indicatorEntities;

}
