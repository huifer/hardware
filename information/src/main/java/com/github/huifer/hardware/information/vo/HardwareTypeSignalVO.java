package com.github.huifer.hardware.information.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Data;


@Data
public class HardwareTypeSignalVO implements Serializable {

  private static final long serialVersionUID = 1L;


  /**
   * 硬件类型表中的硬件类型ID
   */
  @Schema(description =  "硬件类型ID")
  @NotNull(message = "硬件类型ID不能为空")
  private Long typeId;


  /**
   * 硬件信号标识符表中的硬件信号ID
   */
  @Schema(description =  "硬件信号ID")
  @NotNull(message = "硬件信号ID不能为空")
  private Long signalId;



}
