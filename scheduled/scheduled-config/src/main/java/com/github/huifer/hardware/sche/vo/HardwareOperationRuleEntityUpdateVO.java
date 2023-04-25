package com.github.huifer.hardware.sche.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Schema(description = "更新 硬件计算规则")
@EqualsAndHashCode(callSuper = false)
public class HardwareOperationRuleEntityUpdateVO extends HardwareOperationRuleEntityVO implements
    Serializable {

  private static final long serialVersionUID = 1L;

}
