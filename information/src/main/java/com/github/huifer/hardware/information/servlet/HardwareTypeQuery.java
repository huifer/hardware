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
public class HardwareTypeQuery {

  /**
   * 设备类型名称
   **/
  private String name;

}
