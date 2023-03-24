package com.github.huifer.hardware.sche.inf;

import com.github.huifer.hardware.sche.entity.RuleEntity;
import com.github.huifer.hardware.sche.entity.TaskEntity;
import com.github.huifer.hardware.sche.entity.TaskResult;
import com.github.huifer.hardware.sche.entity.TaskResult.CalcResult;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface TaskService {

  /**
   * 设置
   **/
  void save(TaskEntity taskEntity);


  Map<String, BigDecimal> execute(List<RuleEntity> ruleEntities, DataExtractService extractService);

  default TaskResult execTaskEntity(TaskEntity taskEntity, DataExtractService extractService) {
    List<RuleEntity> ruleEntities = taskEntity.getRuleEntities();
    Map<String, BigDecimal> execute = execute(ruleEntities, extractService);
    String id = taskEntity.getId();
    TaskResult taskResult = new TaskResult();
    taskResult.setTaskEntityId(id);
    taskResult.setTaskEntity(taskEntity);
    ArrayList<CalcResult> data1 = new ArrayList<>();

    execute.forEach(
        (k, v) -> {
          CalcResult data = new CalcResult();
          data.setAliasName(k);
          data.setData(v);
          data.setCalcRunTime(LocalDate.now());
          data1.add(data);
        }
    );
    taskResult.setData(data1);
    return taskResult;
  }
}
