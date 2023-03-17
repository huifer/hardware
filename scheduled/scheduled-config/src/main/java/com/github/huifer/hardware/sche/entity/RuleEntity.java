package com.github.huifer.hardware.sche.entity;

import com.github.huifer.hardware.common.enums.ReduceTypeEnums;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
  private List<String> calcParam;
  /**
   * key: 公式参数 value: 设备信号
   **/
  private Map<String, String> calcParamMappingSign;
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
  private Map<String, List<FilterEntity>> calcParamFilter;
  /**
   * 计算优先级，和step有关
   **/
  private int order;

}
