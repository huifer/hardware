package com.github.huifer.hardware.sche.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.Data;

@Data
@Schema(description = "自定义查询 硬件计算规则")
public class HardwareOperationRuleEntityQueryVO implements Serializable {

  private static final long serialVersionUID = 1L;


  /**
   * id
   */
  @Schema(description = "id")
  private Long id;


  /**
   * 公式名称
   */
  @Schema(description = "公式名称")
  private String name;


  /**
   * 公式别名
   */
  @Schema(description = "公式别名")
  private String alias;


  /**
   * 公式
   */
  @Schema(description = "公式")
  private String calc;


  /**
   * 是否为阶段运算值，如果是则需要等最后运算
   */
  @Schema(description = "是否为阶段运算值，如果是则需要等最后运算")
  private Boolean step;


  /**
   * 计算优先级，和step有关
   */
  @Schema(description = "计算优先级，和step有关")
  private Integer order;





}
