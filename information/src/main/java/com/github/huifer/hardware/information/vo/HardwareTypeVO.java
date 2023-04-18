package com.github.huifer.hardware.information.vo;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;


@Data
public class HardwareTypeVO implements Serializable {

  private static final long serialVersionUID = 1L;


  /**
   * 设备详情表主键
   */
  @NotNull(message = "id can not null")
  private Long id;


  /**
   * 硬件类型名称
   */
  private String name;


  /**
   * 硬件类型编码
   */
  private String code;



}
