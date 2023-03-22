package com.github.huifer.hardware.sche.inf;

import com.github.huifer.hardware.sche.entity.RuleEntity;
import com.github.huifer.hardware.sche.entity.TaskEntity;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface TaskService {

  /**
   * 设置
   **/
  void save(TaskEntity taskEntity);


  Map<String, BigDecimal> execute(List<RuleEntity> ruleEntities, DataExtractService extractService);


}
