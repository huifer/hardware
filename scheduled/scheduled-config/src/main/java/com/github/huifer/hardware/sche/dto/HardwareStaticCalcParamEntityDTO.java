package com.github.huifer.hardware.sche.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

@Data
@Schema(description = "公式中的静态参数")
public class HardwareStaticCalcParamEntityDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * id
   */
  @Schema(description = "id")
  private Long id;


  /**
   * 公式参数id
   */
  @Schema(description = "公式参数id")
  private Long calcParamsId;


  /**
   * 公式中的静态参数
   */
  @Schema(description = "公式中的静态参数")
  private BigDecimal staticValue;

  @Schema(description = "创建时间")
  private java.time.LocalDateTime createTime;
}
