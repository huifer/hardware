package com.github.huifer.hardware.sche.inf;

import com.github.huifer.hardware.sche.entity.FilterEntity;
import com.github.huifer.hardware.sche.entity.RuleEntity;
import com.github.huifer.hardware.sche.entity.TaskEntity;
import com.github.huifer.hardware.sche.entity.dto.QueryResponse;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface TaskNoStepService {

  /**
   * 设置
   **/
  void save(TaskEntity taskEntity);


  /**
   * 提取数据
   *
   * @param query 查询对象
   * @return key: 信号标识,value: 查询到的信号值
   **/
  QueryResponse extract(com.github.huifer.hardware.sche.entity.QueryEntity query);

  /**
   * 过滤数据
   *
   * @param data   数据
   * @param filter 过滤对象
   * @return key: 信号标识，value: 过滤后需要进行的数据
   **/
  QueryResponse filter(QueryResponse data, List<FilterEntity> filter);


  Map<String, BigDecimal> execute(List<RuleEntity> ruleEntities);


}
