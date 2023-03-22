package com.github.huifer.hardware.sche.entity;


import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

/**
 * 通过{@link TaskEntity} 得到本实体
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TaskResult implements Serializable {


  @Id
  private Long id;

  /**
   * {@link TaskEntity#id}
   **/
  private Long taskEntityId;
  /**
   * 规则组
   **/
  private RuleEntity ruleEntity;
  /**
   * 规则组运算结果
   */
  private BigDecimal data;

  /**
   * 计算时间
   */
  private String calcRunTime;


}
