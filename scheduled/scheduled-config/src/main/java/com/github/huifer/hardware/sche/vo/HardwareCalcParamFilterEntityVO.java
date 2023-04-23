package com.github.huifer.hardware.sche.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;


@Data
@Schema(description = "保存 公式参数过滤条件")
public class HardwareCalcParamFilterEntityVO implements Serializable {

  private static final long serialVersionUID = 1L;


  /**
   * id
   */
  @NotNull(message = "id不能为空")(message = "id can not null")
  @Schema(description = "id")
  private Long id;


  /**
   * 公式参数id
   */
  @Schema(description = "公式参数id")
  private Long calcParamId;


  /**
   * 范围最大值
   */
  @Schema(description = "范围最大值")
  private BigDecimal rangesMax;


  /**
   * 范围最大值
   */
  @Schema(description = "范围最大值")
  private BigDecimal rangesMin;


  /**
   * 默认左等于 0 左等于 1 右等于
   */
  @Schema(description = "默认左等于 0 左等于 1 右等于")
  private Boolean lefteqRighteqState;



}
