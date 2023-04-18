package com.github.huifer.hardware.information.vo;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;


@Data
public class HardwareSignalMappingVO implements Serializable {

  private static final long serialVersionUID = 1L;


  /**
   * 硬件信号映射关系表主键
   */
  @NotNull(message = "id can not null")
  private Long id;


  /**
   * 硬件设备ID
   */
  private String deviceId;


  /**
   * 硬件输出信号key
   */
  private String signalKey;


  /**
   * 系统指定信号名称
   */
  private String systemSignalName;



}
