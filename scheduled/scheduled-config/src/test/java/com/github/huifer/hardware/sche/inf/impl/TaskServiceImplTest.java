package com.github.huifer.hardware.sche.inf.impl;

import com.github.huifer.hardware.common.enums.ReduceTypeEnums;
import com.github.huifer.hardware.sche.entity.FilterEntity;
import com.github.huifer.hardware.sche.entity.QueryEntity;
import com.github.huifer.hardware.sche.entity.RuleEntity;
import com.github.huifer.hardware.sche.entity.TaskEntity;
import com.github.huifer.hardware.sche.entity.dto.QueryResponse;
import com.github.huifer.hardware.sche.inf.DataExtractService;
import com.github.huifer.hardware.sche.inf.TaskService;
import com.google.gson.Gson;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@SpringBootTest(classes = {CustomerBeans.class})
//@ActiveProfiles(value = {"mongo", "logging"})
class TaskServiceImplTest {

  private static final Logger logger = LoggerFactory.getLogger(TaskServiceImplTest.class);
  private final TaskService taskService = new TaskServiceImpl(null);
  Gson gson = new Gson();
  TaskEntity tsk = null;

  @Test
  public void find() {
    List<RuleEntity> ruleEntities = tsk.getRuleEntities();
    Object execute = taskService.execute(ruleEntities, new TaskServiceATest());
    System.out.println(execute);
  }

  @BeforeEach
  void save() {
    TaskEntity taskEntity = new TaskEntity();
    ArrayList<RuleEntity> ruleEntities = new ArrayList<>();
    RuleEntity e = se();

    ruleEntities.add(e);

    RuleEntity e2 = s2();
    ruleEntities.add(e2);
    RuleEntity e3 = s3();
    ruleEntities.add(e3);


    taskEntity.setRuleEntities(ruleEntities);
    tsk = taskEntity;
    System.out.println(gson.toJson(taskEntity));
  }

  private RuleEntity s3() {
    RuleEntity e = new RuleEntity();
    e.setName("公式3");
    e.setAlias("s3");
    e.setCalc("s2+s1");
    ArrayList<String> calcParam = new ArrayList<>();
    calcParam.add("s1");
    calcParam.add("s2");
    e.setCalcParams(calcParam);

    e.setStep(true);
    e.setOrder(2);
    return e;}


  private RuleEntity s2() {
    RuleEntity e = new RuleEntity();
    e.setName("公式2");
    e.setAlias("s2");
    e.setCalc("s1+1");
    ArrayList<String> calcParam = new ArrayList<>();
    calcParam.add("s1");
    e.setCalcParams(calcParam);

    e.setStep(true);
    e.setOrder(1);
    return e;
  }

  private static RuleEntity se() {
    RuleEntity e = new RuleEntity();
    e.setName("公式一");
    e.setAlias("s1");
    e.setCalc("math.sqrt(a+b)");
    e.setCalcParams(Arrays.stream(new String[]{"a", "b"}).toList());
    e.setStep(false);
    HashMap<String, QueryEntity> calcParamMappingQuery = new HashMap<>();
    QueryEntity value = new QueryEntity();
    value.setSignal("sig_4");
    value.setDeviceId("device_1");
    value.setDeviceType("device_1");
    value.setReduceTypeEnums(ReduceTypeEnums.AVG);
    calcParamMappingQuery.put("a", value);
    calcParamMappingQuery.put("b", value);
    e.setCalcParamMappingQuery(calcParamMappingQuery);
    Map<String, FilterEntity> calcParamFilter = new HashMap<>();
    calcParamFilter.put("a", new FilterEntity());
    e.setCalcParamFilter(calcParamFilter);
    return e;
  }

  private static class TaskServiceATest implements DataExtractService {


    public QueryResponse extract(QueryEntity query) {
      QueryResponse res = new QueryResponse();
      res.setSignle(query.getSignal());
      res.setReduceTypeEnums(query.getReduceTypeEnums());
      List<BigDecimal> r = new ArrayList<>();
      for (int i = 0; i < 10; i++) {
        r.add(BigDecimal.valueOf(i));
      }
      res.setData(r);
      return res;
    }

    public QueryResponse filter(QueryResponse data, List<FilterEntity> filter) {
      return data;
    }
  }

}