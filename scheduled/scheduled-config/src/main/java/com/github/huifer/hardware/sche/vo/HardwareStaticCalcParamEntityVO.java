package com.github.huifer.hardware.sche.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;


@Data
@Schema(description = "保存 公式中的静态参数")
public class HardwareStaticCalcParamEntityVO implements Serializable {

  private static final long serialVersionUID = 1L;





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



}
