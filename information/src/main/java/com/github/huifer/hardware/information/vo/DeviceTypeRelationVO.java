package com.github.huifer.hardware.information.vo;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;


@Data
public class DeviceTypeRelationVO implements Serializable {

  private static final long serialVersionUID = 1L;


  /**
   * 设备详情表主键
   */
  @NotNull(message = "id can not null")
  private Long id;


  /**
   * 设备详情表中的设备ID
   */
  private Long deviceId;


  /**
   * 硬件类型表中的硬件类型ID
   */
  private Long typeId;



}
