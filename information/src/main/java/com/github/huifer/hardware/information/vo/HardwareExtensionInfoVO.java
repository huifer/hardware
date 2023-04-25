package com.github.huifer.hardware.information.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Data;


@Data
@Schema(description = "硬件扩展信息")
public class HardwareExtensionInfoVO implements Serializable {

  private static final long serialVersionUID = 1L;


  /**
   * 硬件设备ID
   */
  @NotNull(message = "硬件设备ID不能为空")
  @Schema(description = "硬件设备ID")
  private Long deviceId;


  /**
   * 扩展信息
   */
  @NotNull(message = "扩展信息不能为空")
  @Schema(description = "扩展信息")
  private String extInfo;



}
