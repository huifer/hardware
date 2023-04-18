package com.github.huifer.hardware.information.dto;


import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class DeviceDetailDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 设备详情表主键
   */
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
