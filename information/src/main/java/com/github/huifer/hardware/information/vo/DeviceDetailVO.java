package com.github.huifer.hardware.information.vo;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;


@Data
public class DeviceDetailVO implements Serializable {

  private static final long serialVersionUID = 1L;


  /**
   * 设备详情表主键
   */
  @NotNull(message = "id can not null")
  private Long id;


  /**
   * 设备名称
   */
  private String name;


  /**
   * 设备地址
   */
  private String address;


  /**
   * 设备所在经度
   */
  private Float latitude;


  /**
   * 设备所在纬度
   */
  private Float longitude;



}
