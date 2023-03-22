# 计算配置

计算配置主要有如下核心实体

1. [FilterEntity.java](src%2Fmain%2Fjava%2Fcom%2Fgithub%2Fhuifer%2Fhardware%2Fsche%2Fentity%2FFilterEntity.java): 过滤实体，用于对提取后的数据进行过滤
1. [IndicatorEntity.java](src%2Fmain%2Fjava%2Fcom%2Fgithub%2Fhuifer%2Fhardware%2Fsche%2Fentity%2FIndicatorEntity.java): 指标实体，存储两个数和命中两个数的名称
1. [IndicatorGroupEntity.java](src%2Fmain%2Fjava%2Fcom%2Fgithub%2Fhuifer%2Fhardware%2Fsche%2Fentity%2FIndicatorGroupEntity.java): 指标组实体，用于承载多个指标实体
1. [QueryEntity.java](src%2Fmain%2Fjava%2Fcom%2Fgithub%2Fhuifer%2Fhardware%2Fsche%2Fentity%2FQueryEntity.java): 数据查询条件实体
1. [RuleEntity.java](src%2Fmain%2Fjava%2Fcom%2Fgithub%2Fhuifer%2Fhardware%2Fsche%2Fentity%2FRuleEntity.java): 计算规则实体
1. [TaskEntity.java](src%2Fmain%2Fjava%2Fcom%2Fgithub%2Fhuifer%2Fhardware%2Fsche%2Fentity%2FTaskEntity.java): 任务实体
1. [TaskResult.java](src%2Fmain%2Fjava%2Fcom%2Fgithub%2Fhuifer%2Fhardware%2Fsche%2Fentity%2FTaskResult.java): 任务执行结果实体



## 核心执行流程

在计算配置中涉及如下几个流程。

1.   提取数据：该流程是指数据从磁盘到内存的过程。

2.   过滤数据：该流程是指将提取数据流程执行后的数据进行清洗的过程，剔除不需要参与后续计算的数据。

3.   步骤数据计算和汇总数据计算：步骤数据计算是指 在复杂公式中需要进行阶段性运算，例如公式
     $$
     s=a+b*z ;z=10*n
     $$
     在这个公式中a,b,n是参数，公式计算首先需要先计算z然后再计算s的数据我们将z的计算称之为步骤计算数据，将s的计算称之为汇总数据计算。





### 提取数据

提取数据方法由`DataExtractService`接口提供，具体处理代码如下

```
public interface DataExtractService {
  /**
   * 提取数据
   *
   * @param query 查询对象
   * @return key: 信号标识,value: 查询到的信号值
   **/
  QueryResponse extract(com.github.huifer.hardware.sche.entity.QueryEntity query);
}
```

`QueryEntity`参数说明：该参数用于表示数据查询对象，核心字段如下。

1. signal：硬件信号名称
2. deviceId：设备id
3. deviceType：设备类型
4. start：提取数据的开始时间
5. end：提取数据的结束时间，默认Long最大值
6. reduceTypeEnums：提取数据后的归并方案（最大值、最小值、平均值、求和）。

`QueryResponse`参数说明：该参数用于表示数据查询结果对象，核心字段如下。

1. signle：硬件信号名称
1. data：查询到的结果集合
1. reduceTypeEnums：提取数据后的归并方案（最大值、最小值、平均值、求和）。如果没有特殊处理请选择和查询条件相同的数据，如果不填将默认采用`QueryEntity`中的归并方案。





### 过滤数据

过滤数据方法由`DataExtractService`接口提供，具体处理代码如下。

```
public interface DataExtractService {

  /**
   * 过滤数据
   *
   * @param data   数据
   * @param filter 过滤对象
   * @return key: 信号标识，value: 过滤后需要进行的数据
   **/
  default QueryResponse filter(QueryResponse data, FilterEntity filter) {
    if (filter != null) {
      for (Iterator<BigDecimal> iterator = data.getData().iterator(); iterator.hasNext(); ) {
        BigDecimal datum = iterator.next();
        if (filter.ignore(datum)) {
          iterator.remove();
        }
      }
      return data;
    }
    return data;
  }
}
```

`QueryResponse`参数说明：该参数用于表示数据查询结果对象。

`FilterEntity`参数说明：该参数用于标识数据过滤条件核心字段如下。

1.   max：最大值

2.   min：最小值

3.   在`FilterEntity`中提供`ignore`方法来判断是否需要过滤数据，当满足下面条件时则不需要过滤数据
     $$
     min<= 输入数据<=max
     $$
     





### 步骤数据计算和汇总数据计算

步骤数据计算和汇总数据计算两者步骤数据计算的优先级高于汇总数据计算，步骤数据和汇总数据计算的前提是经过提取数据和过滤数据。数据计算通过`TaskService`接口作为主入口

```
public interface TaskService {
  Map<String, BigDecimal> execute(List<RuleEntity> ruleEntities, DataExtractService extractService);
}
```

核心参数`RuleEntity`结构如下

| 字段名称             | 字段类型            | 字段说明                                 |
| -------------------- | ------------------- | ---------------------------------------- |
| name                 | String              | 公式名称（中文）                         |
| alias                | String              | 公式别名（常用于步骤计算中的临时变量）   |
| calc                 | String              | 公式表达式，需要符合google aviator要求   |
| calcParam            | List<String>        | 公式中出现的参数，可以超过公式中所需参数 |
| calcParamMappingSign | Map<String, String> | 公式                                     |
|                      |                     |                                          |
|                      |                     |                                          |
|                      |                     |                                          |



