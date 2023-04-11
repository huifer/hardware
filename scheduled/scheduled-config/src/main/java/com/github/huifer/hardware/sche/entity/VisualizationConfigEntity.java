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
 * 可视化系数实体，用于返回前端使用
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@EqualsAndHashCode
@ToString
public class VisualizationConfigEntity implements Serializable {

  @Id
  private String id;
  /**
   * 规则id
   **/
  private String ruleEntityId;
  /**
   * 每个时间所需要的系数
   **/
  private List<TimeFactor> timeFactors;
  /**
   * 评分规则id
   **/
  private List<String> ratingEntityIds;


  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @FieldNameConstants
  @EqualsAndHashCode
  @ToString
  public static class TimeFactor {

    private BigDecimal k = BigDecimal.ONE;

    /**
     * 作用开始时间
     */
    private LocalDate actionStartTime;

    /**
     * 作用截止时间
     */
    private LocalDate actionEndTime;

    public boolean inActionTime(LocalDate date) {
      if (date == null) {
        return false;
      }
      return !date.isBefore(actionStartTime) && !date.isAfter(actionEndTime);
    }
  }

//  public List<BigDecimal> calculateResult(TaskResult taskResult) {
//    List<BigDecimal> result = new ArrayList<>();
//    Map<LocalDate, List<TimeFactor>> timeFactorMap = groupTimeFactorsByActionTime();
//
//    // 初始化计算结果列表
//    for (int i = 0; i < timeFactorMap.size(); i++) {
//      result.add(BigDecimal.ZERO);
//    }
//
//    LocalDate calcRunTime = taskResult.getCalcRunTime();
//    // 遍历规则计算结果
//    for (TaskResult.CalcResult calcResult : taskResult.getData()) {
//      LocalDate t = calcRunTime;
//      BigDecimal d = calcResult.getData();
//
//      // 找到规则计算结果所对应的时间区间
//      List<TimeFactor> timeFactors = timeFactorMap.get(t);
//
//      // 将计算结果累加到对应时间区间的结果中
//      for (TimeFactor timeFactor : timeFactors) {
//        BigDecimal factor = timeFactor.getK();
//        BigDecimal temp = d.multiply(factor);
//      }
//    }
//    return result;
//  }
//
//  // 按照作用时间区间分组
//  private Map<LocalDate, List<TimeFactor>> groupTimeFactorsByActionTime() {
//    Map<LocalDate, List<TimeFactor>> map = new HashMap<>();
//    for (TimeFactor timeFactor : timeFactors) {
//      LocalDate startTime = timeFactor.getActionStartTime();
//      LocalDate endTime = timeFactor.getActionEndTime();
//      for (LocalDate date = startTime; !date.isAfter(endTime); date = date.plusDays(1)) {
//        List<TimeFactor> list = map.getOrDefault(date, new ArrayList<>());
//        list.add(timeFactor);
//        map.put(date, list);
//      }
//    }
//    return map;
//  }
}
