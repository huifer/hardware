package com.github.huifer.hardware.sche.entity.dto;

import com.github.huifer.hardware.common.enums.ReduceTypeEnums;
import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QueryResponse {

  /**
   * 信号
   **/
  private String signle;


  /**
   * 数据
   **/
  private List<BigDecimal> data;

  /**
   * 单个信号数据归并原则
   **/
  private ReduceTypeEnums reduceTypeEnums;

}
