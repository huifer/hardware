package com.github.huifer.hardware.information.vo;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;


@Data
public class HardwareExtensionInfoVO implements Serializable {

  private static final long serialVersionUID = 1L;


  /**
   * 硬件扩展信息表主键
   */
  @NotNull(message = "id can not null")
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
