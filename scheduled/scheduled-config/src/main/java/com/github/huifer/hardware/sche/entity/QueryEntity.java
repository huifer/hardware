package com.github.huifer.hardware.sche.entity;

import com.github.huifer.hardware.common.enums.ReduceTypeEnums;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 查询条件实体
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QueryEntity implements Serializable {


  /**
   * 信号
   **/
  private String signal;
  private String deviceId;
  private String deviceType;

  /**
   * 开始时间
   **/
  private long start;
  /**
   * 结束时间
   **/
  private long end = Long.MAX_VALUE;

  /**
   * 单个信号数据归并原则
   **/
  private ReduceTypeEnums reduceTypeEnums;


}
