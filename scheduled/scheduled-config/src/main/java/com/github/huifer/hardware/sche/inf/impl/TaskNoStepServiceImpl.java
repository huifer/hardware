package com.github.huifer.hardware.sche.inf.impl;

import com.github.huifer.hardware.common.enums.ReduceTypeEnums;
import com.github.huifer.hardware.sche.entity.FilterEntity;
import com.github.huifer.hardware.sche.entity.QueryEntity;
import com.github.huifer.hardware.sche.entity.RuleEntity;
import com.github.huifer.hardware.sche.entity.TaskEntity;
import com.github.huifer.hardware.sche.entity.dto.QueryResponse;
import com.github.huifer.hardware.sche.inf.TaskNoStepService;
import com.googlecode.aviator.AviatorEvaluator;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.transaction.annotation.Transactional;

public class TaskNoStepServiceImpl implements TaskNoStepService {


  private static final Logger logger = LoggerFactory.getLogger(TaskNoStepServiceImpl.class);
  private final MongoTemplate mongoTemplate;

  public TaskNoStepServiceImpl(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  @Transactional(rollbackFor = {Exception.class})
  @Override
  public void save(TaskEntity taskEntity) {
    this.mongoTemplate.save(taskEntity, "task");
  }

  @Override
  public QueryResponse extract(QueryEntity query) {
    return null;
  }

  @Override
  public QueryResponse filter(QueryResponse data,
      List<FilterEntity> filter) {
    return null;
  }

  @Override
  public Map<String, BigDecimal> execute(List<RuleEntity> ruleEntities) {

    // 2. 等待 step 为false 的数值计算
    List<RuleEntity> ruleEntitiesFalse = ruleEntities.stream().filter(s -> !s.isStep())
        .toList();

    Map<String, BigDecimal> fd = new HashMap<>();
    for (RuleEntity ruleEntity : ruleEntitiesFalse) {
      Map<String, BigDecimal> bigDecimalMap = executeStepFalse(ruleEntity);
      fd.putAll(bigDecimalMap);
    }
    // 3. 计算 step 为 true 的数值
    List<RuleEntity> collect = ruleEntities.stream().filter(RuleEntity::isStep)
        .toList();

    List<RuleEntity> collect1 = collect.stream()
        .sorted(Comparator.comparingInt(RuleEntity::getOrder)).collect(Collectors.toList());
    Map<String, BigDecimal> bigDecimalMap = executeStepTrue(collect1, fd);

    return bigDecimalMap;
  }

  /**
   * 执行step为true的计算
   **/
  private Map<String, BigDecimal> executeStepTrue(List<RuleEntity> ruleEntitiesTrue,
      Map<String, BigDecimal> fd) {
    Map<String, Object> param = new HashMap<>();
    fd.forEach((k, v) -> {
      param.put(k, v);
    });

    for (RuleEntity ruleEntity : ruleEntitiesTrue) {
      Object execute = AviatorEvaluator.execute(ruleEntity.getCalc(), param);
      // FIXME: 2023/3/17 key值确定
      String name = ruleEntity.getAlias();
      fd.put(name, new BigDecimal(execute.toString()));
      param.put(name, new BigDecimal(execute.toString()));
    }
    return fd;
  }

  /**
   * 执行step为false的计算
   *
   * @param ruleEntity 规则
   * @return key: 公式别名，value：公式运算结果
   */
  public Map<String, BigDecimal> executeStepFalse(RuleEntity ruleEntity) {
    if (!ruleEntity.isStep()) {

      Map<String, QueryEntity> calcParamMappingQuery = ruleEntity.getCalcParamMappingQuery();
      Map<String, List<FilterEntity>> calcParamFilter = ruleEntity.getCalcParamFilter();

      List<QueryResponse> queryResponses = new ArrayList<>();
      calcParamMappingQuery.forEach((k, v) -> {
        queryResponses.add(filter(extract(v), calcParamFilter.get(k)));
      });
      // key: 公式名称,value 值
      Map<String, BigDecimal> res = new HashMap<>();
      // 非步骤运算
      // 核心计算
      BigDecimal calc = calc(ruleEntity.getCalc(), queryResponses,
          ruleEntity.getCalcParamMappingSign());
      // FIXME: 2023/3/17 key值确定
      String name = ruleEntity.getAlias();
      res.put(name, calc);
      return res;
    }

    return null;
  }

  private BigDecimal calc(String calc, List<QueryResponse> response,
      Map<String, String> calcParamMappingSign) {
    Map<String, Object> bigDecimalMap = new HashMap<>();

    for (Entry<String, String> entry : calcParamMappingSign.entrySet()) {
      String k = entry.getKey();
      String v = entry.getValue();
      QueryResponse query = selectCalcData(response, v);
      bigDecimalMap.put(k, reduce(query.getReduceTypeEnums(), query.getData()));
    }

    Object execute = AviatorEvaluator.execute(calc, bigDecimalMap);
    return new BigDecimal(execute.toString());
  }

  private QueryResponse selectCalcData(List<QueryResponse> response, String v) {
    for (QueryResponse queryResponse : response) {
      if (queryResponse.getSignle().equals(v)) {
        return queryResponse;
      }
    }
    return null;
  }


  private BigDecimal reduce(ReduceTypeEnums reduceTypeEnums, List<BigDecimal> calcData) {
    BigDecimal d = BigDecimal.ZERO;
    if (Objects.requireNonNull(reduceTypeEnums) == ReduceTypeEnums.AVG) {
      d = average(calcData, RoundingMode.UP);
    } else if (reduceTypeEnums == ReduceTypeEnums.MAX) {
      Optional<BigDecimal> max = calcData.stream()
          .max(BigDecimal::compareTo);

      if (max.isPresent()) {
        d = max.get();
      }
    } else if (reduceTypeEnums == ReduceTypeEnums.MIN) {
      Optional<BigDecimal> min = calcData.stream()
          .min(BigDecimal::compareTo);
      if (min.isPresent()) {
        d = min.get();
      }
    } else if (reduceTypeEnums == ReduceTypeEnums.SUM) {
      d = calcData.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    return d;

  }

  public BigDecimal average(List<BigDecimal> bigDecimals, RoundingMode roundingMode) {
    BigDecimal sum = bigDecimals.stream()
        .map(Objects::requireNonNull)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
    long count = bigDecimals.stream().filter(Objects::nonNull).count();
    try {
      BigDecimal divide = sum.divide(new BigDecimal(count), 10, roundingMode);
      logger.info("求和结果= {} , 数量 = {} 商 = {}", sum, count, divide);
      return divide;
    } catch (Exception e) {
      return BigDecimal.ZERO;
    }
  }
}
