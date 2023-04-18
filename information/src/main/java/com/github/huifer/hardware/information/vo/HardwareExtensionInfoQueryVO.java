package com.github.huifer.hardware.information.vo;


import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class HardwareExtensionInfoQueryVO implements Serializable {

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
