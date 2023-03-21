package com.github.huifer.hardware.information.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 硬件扩展信息
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HardwareInfoExtensionsEntity {

  /**
   * 当前属性是否使用
   **/
  private boolean using = false;


  /**
   * 硬件供应商提供的设备id
   **/
  private String deviceId;



}
