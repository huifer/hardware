package com.github.huifer.hardware.sche.entity;


import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
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
  private String taskEntityId;
  /**
   * 任务详情
   **/
  private TaskEntity taskEntity;
  /**
   * 规则组运算结果
   */
  private List<CalcResult> data;

  /**
   * 计算时间
   */
  private LocalDate calcRunTime;



  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @FieldNameConstants
  @EqualsAndHashCode
  @ToString
  public static class CalcResult {

    /**
     * 公式别名
     */
    private String aliasName;
    /**
     * 公式计算结果
     **/
    private BigDecimal data;
  }


}
