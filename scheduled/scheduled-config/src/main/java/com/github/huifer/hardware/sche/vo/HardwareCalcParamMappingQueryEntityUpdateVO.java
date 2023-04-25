package com.github.huifer.hardware.sche.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "更新 公式所需要参数")
@lombok.EqualsAndHashCode(callSuper = false)
public class HardwareCalcParamMappingQueryEntityUpdateVO extends
    HardwareCalcParamMappingQueryEntityVO implements java.io.Serializable {

  private static final long serialVersionUID = 1L;

}
