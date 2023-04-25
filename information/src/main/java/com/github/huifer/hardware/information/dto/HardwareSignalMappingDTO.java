package com.github.huifer.hardware.information.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.Data;

@Data
public class HardwareSignalMappingDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 硬件信号映射关系表主键
   */
  @Schema(description = "id")
  private Long id;


  /**
   * 硬件设备ID
   */
  @Schema(description = "硬件设备id")
  private String deviceId;


  /**
   * 硬件输出信号key
   */
  @Schema(description = "硬件输出信号key")
  private String signalKey;


  /**
   * 系统指定信号名称
   */
  @Schema(description = "系统指定信号名称")
  private String systemSignalName;



}
