package com.github.huifer.hardware.sche.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Schema(description = "更新 公式所需要参数查询时间")
@EqualsAndHashCode(callSuper = false)
public class HardwareCalcParamMappingQueryTimeEntityUpdateVO extends
    HardwareCalcParamMappingQueryTimeEntityVO implements Serializable {

  private static final long serialVersionUID = 1L;

}
