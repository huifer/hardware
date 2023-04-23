package com.github.huifer.hardware.information.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class HardwareSignalMappingUpdateVO   implements Serializable {

  private static final long serialVersionUID = 1L;



  /**
   * 硬件设备ID
   */
  @Schema(description = "硬件设备ID")
  @NotNull(message = "硬件设备不能为空")
  private Long deviceId;


  /**
   * 硬件输出信号key
   */
  @Schema(description = "硬件输出信号key")
  @NotNull(message = "硬件输出信号key")
  private String signalKey;


  /**
   * 系统指定信号名称
   */
  @Schema(description = "系统指定信号名称")
  @NotNull(message = "系统指定信号名称")
  private String systemSignalName;

}
