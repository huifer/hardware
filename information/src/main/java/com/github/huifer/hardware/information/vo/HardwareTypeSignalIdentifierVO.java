package com.github.huifer.hardware.information.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.Data;


@Data
@Schema(description = "硬件类型和信号值")
public class HardwareTypeSignalIdentifierVO implements Serializable {

  private static final long serialVersionUID = 1L;




  /**
   * 硬件类型表中的硬件类型ID
   */
  @Schema(description = "硬件类型ID")
  private Long typeId;


  /**
   * 硬件信号标识符表中的硬件信号ID
   */
  @Schema(description = "硬件信号标识符ID")
  private Long signalId;



}
