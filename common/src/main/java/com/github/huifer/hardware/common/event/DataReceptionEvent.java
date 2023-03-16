package com.github.huifer.hardware.common.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * 外部数据到本系统后的事件
 */
@Getter
@Setter
public class DataReceptionEvent extends ApplicationEvent {

  /**
   * 数据对象
   **/
  private String data;
  /**
   * 设备id
   */
  private String deviceId;
  /**
   * 设备类型
   */
  private String deviceType;


  public DataReceptionEvent(String data, String deviceId, String deviceType) {
    super(data);
    this.data = data;
    this.deviceId = deviceId;
    this.deviceType = deviceType;
  }


}
