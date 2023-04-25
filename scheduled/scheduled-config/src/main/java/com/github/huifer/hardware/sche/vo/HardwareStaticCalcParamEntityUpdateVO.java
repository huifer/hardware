package com.github.huifer.hardware.sche.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Schema(description = "更新 公式中的静态参数")
@EqualsAndHashCode(callSuper = false)
public class HardwareStaticCalcParamEntityUpdateVO extends
    HardwareStaticCalcParamEntityVO implements Serializable {

  private static final long serialVersionUID = 1L;

}
