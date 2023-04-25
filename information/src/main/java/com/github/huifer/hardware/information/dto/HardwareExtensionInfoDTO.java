package com.github.huifer.hardware.information.dto;


import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class HardwareExtensionInfoDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 硬件扩展信息表主键
   */
  private Long id;


  /**
   * 硬件设备ID
   */
  private Long deviceId;


  /**
   * 扩展信息
   */
  private String extInfo;



}
