package com.github.huifer.hardware.sc.influx;

import lombok.Data;

@Data
public class SignQueryResponse {

  private String deviceType;

  private String deviceId;

  private String key;

  private String value;


  /**
   * 时间区域
   */
  private String timeRange;

  private long start ;

  private long end;


}
