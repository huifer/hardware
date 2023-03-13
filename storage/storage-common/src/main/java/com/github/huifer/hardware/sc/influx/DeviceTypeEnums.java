package com.github.huifer.hardware.sc.influx;

public enum DeviceTypeEnums {
  LIULIANG("流量计", "liuliang"),
  YEWEI("液位计", "yewei"),
  ;
  private String name;
  private String code;

  public String getName() {
    return name;
  }

  public static DeviceTypeEnums conv(String code) {
    for (DeviceTypeEnums value : DeviceTypeEnums.values()) {
      if (value.code.equals(code)) {
        return value;
      }
    }
    return null;
  }
  public String getCode() {
    return code;
  }

  DeviceTypeEnums(String name, String code) {
    this.name = name;
    this.code = code;
  }
}
