package com.github.huifer.hardware.sche.entity;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

/**
 * 任务实体 1. 根据查询条件做数据查询 2.
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TaskEntity implements Serializable {

 @Id
 private String id;

 private List<String> ruleIds;
 /**
  * 规则
  */
 private List<RuleEntity> ruleEntities;

}
