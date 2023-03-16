package com.github.huifer.hardware.sc.entity;

public enum DeviceTypeEnums {
  LIULIANG("流量计", "LIULIANG"),
  YEWEI("液位计", "YEWEI"),
  ;
  private final String name;
  private final String code;

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
