package com.github.huifer.hardware.information.dto;


import java.io.Serializable;
import lombok.Data;

@Data
public class HardwareTypeSignalDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 关系主键
   */

  private Long id;


  /**
   * 硬件类型表中的硬件类型ID
   */
  private Long typeId;


  /**
   * 硬件信号标识符表中的硬件信号ID
   */
  private Long signalId;



}
