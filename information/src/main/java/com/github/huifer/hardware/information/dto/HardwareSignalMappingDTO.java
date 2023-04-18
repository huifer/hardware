package com.github.huifer.hardware.information.dto;


import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class HardwareSignalMappingDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 硬件信号映射关系表主键
   */
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
