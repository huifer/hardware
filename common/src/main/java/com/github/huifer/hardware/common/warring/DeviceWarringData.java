package com.github.huifer.hardware.common.warring;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 设备报警实体
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DeviceWarringData {

  /**
   * 设备唯一标识
   **/
  private String deviceId;
  /**
   * 设备类型
   **/
  private String deviceType;
  /**
   * 设备信号
   **/
  private String signal;
  /**
   * 信号值
   **/
  private String value;
  /**
   * 设备报警最大值
   **/
  private String max;
  /**
   * 设备报警最小值
   **/
  private String min;

}
