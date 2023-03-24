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

| 字段名称              | 字段类型                  | 字段说明                                                     |
| --------------------- | ------------------------- | ------------------------------------------------------------ |
| name                  | String                    | 公式名称（中文）                                             |
| alias                 | String                    | 公式别名（常用于步骤计算中的临时变量）                       |
| calc                  | String                    | 公式表达式，需要符合google aviator要求(如果没有规则运算填写，规则运算是指当XXX大于某一个值的时候用一个公式，反之则用另一个公式) |
| calcParam             | List<String>              | 公式中出现的参数，可以超过公式中所需参数                     |
| step                  | boolean                   | 是否是步骤运算                                               |
| order                 | int                       | 步骤运算的先后顺序                                           |
| calcParamMappingQuery | Map<String, QueryEntity>  | key:公式中的参数，value：查询条件                            |
| calcParamFilter       | Map<String, FilterEntity> | key:公式中的参数，value：过滤条件                            |
| calcFormulaRule       | List<CalcFormulaRule>     | 数学公式集合（需要通过该集合判断所需使用的公式）             |

`CalcFormulaRule` 结构如下

| 字段名称              | 字段类型                   | 字段说明         |
| --------------------- | -------------------------- | ---------------- |
| calc                  | String                     | 数学公式         |
| calcFormulaParamRules | List<CalcFormulaParamRule> | 公式参数区间条件 |

`CalcFormulaParamRule`结构如下

| 字段名称  | 字段类型       | 字段说明         |
| --------- | -------------- | ---------------- |
| calcParam | String         | 公式内的参数名称 |
| ranges    | List<BigRange> | 参数区间范围     |



运算核心流程：

1.   将`RuleEntity`根据`step`字段进行分组
2.   计算`step`字段为false的数据，将结果存储在Map容器中，存储结构：key：公式别名，value：公式计算值
3.   排序`step`字段为true的数据，从小到大。
4.   将第2步中的数据带入计算的到结果。步骤运算仅限使用step为false的数据进行计算。

公式提取流程：

1.   通过`DataExtractService`配合`RuleEntity`中的`calcParamMappingQuery`查询公式参数和运算原始数据集合。
2.   将第一步中的数据根据公式参数分组。并且将数据配合`ReduceTypeEnums`进行归并。
3.   遍历`calcFormulaRule`将第2步中的数据就那些判断，如果命中则将公式返回。注意：目前公式只会有一个结果。

## 可视化数据存储流程

可视化数据存储分为如下几部分

1.   可视化配置和评分配置
2.   可视化配置与公式运算结果整合





### 可视化配置和评分配置

可视化配置和计算规则进行绑定，每一个计算规则都独立的可视化配置方案。可视化配置采用如下公式进行动态配置
$$
系数*计算规则结果值=可视化数据
$$
注意：系数是有有效范围的，他的有效范围和计算规则结果值运算时间直接关联，只有计算规则结果值的计算时间属于有效范围内才需要做系数乘法。系数默认为1。具体类为`VisualizationConfigEntity`



评分配置是是对计算规则结果值进行名称设置工具，他的作用可以理解为考试环境下的评级，案例如下。

| 最低分 | 最高分 | 评分名称 |
| ------ | ------ | -------- |
| 0      | 60     | 不合格   |
| 61     | 80     | 合格     |
| 80     | 100    | 优秀     |

上述最低分、最高分和评分名称组合起来称之为评分配置，数据承载类：`RatingEntity`。

评分配置和可视化配置是包含关系，一个可视化配置中包含多个评分配置。





### 可视化配置与公式运算结果整合

公式运算结果由`TaskResult`类进行存储，关键信息如下

```java
public static class CalcResult {
    /**
     * 公式别名
     */
    private String aliasName;
    /**
     * 公式计算结果
     **/
    private BigDecimal data;

    /**
     * 计算时间
     */
    private LocalDate calcRunTime;
}

```

可视化配置与公式的关系是一对一，当上述公式计算结果中的`calcRunTime`位于可视化配置信息中的时间范围内则需要进行系数乘法。完成乘法运算后需要进行评分，评分分为两种：

1.   原始结果评分
2.   乘以系数后评分

最终数据呈现方式如下

```java

public class VisualizationResultEntity {

    @Id
    private String id;

    private String visualizationFactorEntityId;

    /**
   * 原始公式运算结果，
   **/
    private List<CalcResult> data;
    /**
   * 评分明细（对原始数据）
   **/
    private List<RatingResult> dataRating;
    /**
   * 配合系数运算过后的数据
   **/
    private List<CalcResult> kData;
    /**
   * 评分明细（对原始数据,带系数）
   **/
    private List<RatingResult> kDataRating;

    public static class RatingResult {

        /**
     * {@link RatingEntity#id}
     */
        private String ratingId;
        /**
     * 评分规则名称 {@link RatingEntity#name}
     **/
        private String ratingName;
        /**
     * 最终评分名称 {@link BigRange#name}
     **/
        private String ratingResultName;
    }

}
```

