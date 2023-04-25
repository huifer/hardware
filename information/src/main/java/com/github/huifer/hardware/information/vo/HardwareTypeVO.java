package com.github.huifer.hardware.information.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Data;


@Data
@Schema(description = "设备类型")
public class HardwareTypeVO implements Serializable {

  private static final long serialVersionUID = 1L;




  /**
   * 硬件类型名称
   */
  @NotNull(message = "类型名称不能为空")
  @Schema(description = "类型名称不能为空")
  private String name;

  @Schema(description = "设备类型状态 0正常 1停止使用")
  private Boolean state;
  /**
   * 硬件类型编码
   */
  @NotNull(message = "类型编码不能为空")
  @Schema(description = "类型编码不能为空")
  private String code;



}
