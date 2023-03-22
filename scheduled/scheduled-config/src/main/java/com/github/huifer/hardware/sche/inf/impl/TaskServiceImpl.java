package com.github.huifer.hardware.sche.inf.impl;

import com.github.huifer.hardware.common.enums.ReduceTypeEnums;
import com.github.huifer.hardware.common.utils.GsonFactory;
import com.github.huifer.hardware.sche.entity.FilterEntity;
import com.github.huifer.hardware.sche.entity.QueryEntity;
import com.github.huifer.hardware.sche.entity.RuleEntity;
import com.github.huifer.hardware.sche.entity.RuleEntity.CalcFormulaParamRule;
import com.github.huifer.hardware.sche.entity.RuleEntity.CalcFormulaRule;
import com.github.huifer.hardware.sche.entity.TaskEntity;
import com.github.huifer.hardware.sche.entity.dto.QueryResponse;
import com.github.huifer.hardware.sche.event.DataCalculationCompletedEvent;
import com.github.huifer.hardware.sche.event.DataExtractionCompletedEvent;
import com.github.huifer.hardware.sche.event.DataFilteringCompletedEvent;
import com.github.huifer.hardware.sche.inf.DataExtractService;
import com.github.huifer.hardware.sche.inf.TaskService;
import com.google.gson.Gson;
import com.googlecode.aviator.AviatorEvaluator;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

public class TaskServiceImpl implements TaskService {


  private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);
  private final MongoTemplate mongoTemplate;
  Gson gson = GsonFactory.getGson();
  ThreadLocal<String> uid = new ThreadLocal<>();

  @Transactional(rollbackFor = {Exception.class})
  @Override
  public void save(TaskEntity taskEntity) {
    this.mongoTemplate.save(taskEntity, "task");
  }


  public TaskServiceImpl(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }


  /**
   * 计算公式计算器
   *
   * @param ruleEntity
   * @return
   */
  private String getCalc(RuleEntity ruleEntity, List<QueryResponse> responses) {
    // TODO: 2023/3/22 计算规则
    // 1. 将数据归并
    Map<String, BigDecimal> bigDecimalMap = new HashMap<>();
    if (!CollectionUtils.isEmpty(responses)) {
      for (QueryResponse respons : responses) {
        BigDecimal reduce = reduce(respons.getReduceTypeEnums(), respons.getData());
        bigDecimalMap.put(respons.getSignle(), reduce);
      }
    }
    // 2. 归并后数据确认公式
    List<CalcFormulaRule> calcFormulaRule = ruleEntity.getCalcFormulaRule();
    if (!CollectionUtils.isEmpty(calcFormulaRule)) {

      for (CalcFormulaRule formulaRule : calcFormulaRule) {
        List<CalcFormulaParamRule> calcFormulaParamRules = formulaRule.getCalcFormulaParamRules();

        Set<Boolean> booleans = new HashSet<>();
        for (CalcFormulaParamRule calcFormulaParamRule : calcFormulaParamRules) {
          String calcParam = calcFormulaParamRule.getCalcParam();
          boolean between = calcFormulaParamRule.between(bigDecimalMap.get(calcParam));
          booleans.add(between);
        }
        if (booleans.size() == 1 && booleans.contains(true)) {
          return formulaRule.getCalc();
        }

      }
    }
    return ruleEntity.getCalc();
  }

  @Autowired
  private ApplicationEventPublisher applicationEventPublisher;

  /**
   * 执行step为true的计算
   **/
  private Map<String, BigDecimal> executeStepTrue(List<RuleEntity> ruleEntitiesTrue,
      Map<String, BigDecimal> fd) {
    Map<String, Object> param = new HashMap<>();
    if (!CollectionUtils.isEmpty(fd)) {

      fd.forEach((k, v) -> {
        param.put(k, v);
      });
    }
    for (RuleEntity ruleEntity : ruleEntitiesTrue) {
      String calc = getCalc(ruleEntity, null);
      if (!org.apache.commons.lang3.StringUtils.isEmpty(calc)) {
        this.applicationEventPublisher.publishEvent(new DataExtractionCompletedEvent());
        Object execute = AviatorEvaluator.execute(calc, param);

        // FIXME: 2023/3/17 key值确定
        String name = ruleEntity.getAlias();
        fd.put(name, new BigDecimal(execute.toString()));
        param.put(name, new BigDecimal(execute.toString()));
        this.applicationEventPublisher.publishEvent(new DataCalculationCompletedEvent());
      } else {
        String name = ruleEntity.getAlias();
        fd.put(name, BigDecimal.ZERO);
        param.put(name, BigDecimal.ZERO);

      }
    }
    return fd;
  }

  public Map<String, BigDecimal> execute(List<RuleEntity> ruleEntities,
      DataExtractService extractService) {
    genUid();
    // 2. 等待 step 为false 的数值计算
    List<RuleEntity> ruleEntitiesFalse = ruleEntities.stream().filter(s -> !s.isStep())
        .toList();

    Map<String, BigDecimal> fd = new HashMap<>();
    for (RuleEntity ruleEntity : ruleEntitiesFalse) {
      Map<String, BigDecimal> bigDecimalMap = executeStepFalse(ruleEntity, extractService);
      fd.putAll(bigDecimalMap);
    }
    // 3. 计算 step 为 true 的数值
    List<RuleEntity> collect = ruleEntities.stream().filter(RuleEntity::isStep)
        .toList();

    List<RuleEntity> collect1 = collect.stream()
        .sorted(Comparator.comparingInt(RuleEntity::getOrder)).collect(Collectors.toList());

    return executeStepTrue(collect1, fd);
  }

  // TODO: 2023/3/22 查询结果和计算参数直接的关系处理
  private BigDecimal calc(String calc, List<QueryResponse> response,
      Map<String, String> calcParamMappingSign, Map<String, BigDecimal> staticCalcParam) {
    Map<String, Object> bigDecimalMap = new HashMap<>();

    for (Entry<String, String> entry : calcParamMappingSign.entrySet()) {
      String k = entry.getKey();
      String v = entry.getValue();
      QueryResponse query = selectCalcData(response, v);
      bigDecimalMap.put(k, reduce(query.getReduceTypeEnums(), query.getData()));
    }
    if (staticCalcParam != null) {
      bigDecimalMap.putAll(staticCalcParam);
    }

    try {

      Object execute = AviatorEvaluator.execute(calc, bigDecimalMap);
      return new BigDecimal(execute.toString());
    } catch (Exception e) {
      logger.error("calc,calc = {}, response = {}, calcParamMappingSign = {}", gson.toJson(calc),
          gson.toJson(response), gson.toJson(calcParamMappingSign));
      logger.error("计算异常 ", e);
    }
    return BigDecimal.ZERO;
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

  /**
   * 执行step为false的计算
   *
   * @param ruleEntity 规则
   * @return key: 公式别名，value：公式运算结果
   */
  public Map<String, BigDecimal> executeStepFalse(RuleEntity ruleEntity,
      DataExtractService extractService) {
    if (!ruleEntity.isStep()) {

      Map<String, QueryEntity> calcParamMappingQuery = ruleEntity.getCalcParamMappingQuery();
      Map<String, FilterEntity> calcParamFilter = ruleEntity.getCalcParamFilter();

      List<QueryResponse> queryResponses = new ArrayList<>();
      // 参数和信号映射关系
      Map<String, String> calcParamMappingSign = new HashMap<>();
      for (Entry<String, QueryEntity> entry : calcParamMappingQuery.entrySet()) {
        String k = entry.getKey();
        QueryEntity v = entry.getValue();
        QueryResponse extract = extractService.extract(v);
        // 数据提取完成
        this.applicationEventPublisher.publishEvent(new DataExtractionCompletedEvent());
        if (extract.getReduceTypeEnums() == null) {
          extract.setReduceTypeEnums(v.getReduceTypeEnums());
        }
        QueryResponse filter = extractService.filter(extract, calcParamFilter.get(k));
        // 数据过滤完成
        this.applicationEventPublisher.publishEvent(new DataFilteringCompletedEvent());
        queryResponses.add(filter);
        calcParamMappingSign.put(k, filter.getSignle());
      }
      // key: 公式名称,value 值
      Map<String, BigDecimal> res = new HashMap<>();
      // 非步骤运算
      // 核心计算
      String calc1 = getCalc(ruleEntity, queryResponses);
      if (!org.apache.commons.lang3.StringUtils.isEmpty(calc1)) {

        BigDecimal calc = calc(calc1, queryResponses,
            calcParamMappingSign, ruleEntity.getStaticCalcParam());
        // 数据计算完成
        this.applicationEventPublisher.publishEvent(new DataCalculationCompletedEvent());
        // FIXME: 2023/3/17 key值确定
        String name = ruleEntity.getAlias();
        res.put(name, calc);
      } else {
        String name = ruleEntity.getAlias();
        res.put(name, BigDecimal.ZERO);
      }
      return res;

    }

    return null;
  }

  private void genUid() {
    uid.remove();
    uid.set(UUID.randomUUID().toString());
  }

  private String getUid() {
    return uid.get();
  }
}
