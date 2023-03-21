package com.github.huifer.hardware.information.servlet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HardwareInfoQuery {

  private String deviceName;
  private String deviceType;

  private String deviceId;
  private String uid;

}
