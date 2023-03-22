package com.github.huifer.hardware.sche.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.Id;

/**
 * 规则
 **/

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RuleEntity implements Serializable {

  @Id
  private String id;
  /**
   * 公式名称
   **/
  private String name;
  /**
   * 公式别名
   **/
  private String alias;
  /**
   * 公式
   **/
  private String calc;

  /**
   * 公式所需参数
   **/
  private List<String> calcParams;

  /**
   * 是否是阶段运算值，如果是则需要等最后运算
   **/
  private boolean step = false;

  /**
   * key:公式中的参数，value：查询条件
   **/
  private Map<String, QueryEntity> calcParamMappingQuery;
  /**
   * key:公式中的参数，value：过滤条件
   **/
  private Map<String, FilterEntity> calcParamFilter;
  /**
   * 计算优先级，和step有关
   **/
  private int order;

  /**
   * 数学公式集合
   **/
  private List<CalcFormulaRule> calcFormulaRule;

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @FieldNameConstants
  @EqualsAndHashCode
  @ToString
  public static class CalcFormulaRule {

    /**
     * 数学公式
     **/
    private String calc;
    private List<CalcFormulaParamRule> calcFormulaParamRules;
  }

  /**
   * 公式参数区间条件
   **/
  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @FieldNameConstants
  @EqualsAndHashCode
  @ToString
  public static class CalcFormulaParamRule {

    /**
     * 公式参数
     */
    private String calcParam;

    /**
     * 区间
     */
    private List<BigRange> ranges;


    /**
     * 判断 min <= source <= max
     **/
    private boolean between(BigDecimal source, BigDecimal max, BigDecimal min) {
      return (source.compareTo(max) < 1) &&
          (source.compareTo(min) > -1);
    }

    public boolean between(BigDecimal source) {
      if (ranges != null) {
        List<Boolean> booleans = new ArrayList<>();

        for (BigRange range : ranges) {
          booleans.add(between(source, range.getMax(), range.getMin()));
        }
        return !booleans.contains(true);
      } else {
        return false;
      }
      // 如果true不在里面则表示数据需要忽略
    }

  }

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @ToString
  public static class BigRange {

    private BigDecimal max;
    private BigDecimal min;
  }

}
