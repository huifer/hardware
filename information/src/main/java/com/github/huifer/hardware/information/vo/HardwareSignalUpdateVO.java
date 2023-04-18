package com.github.huifer.hardware.information.vo;


import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class HardwareSignalUpdateVO extends HardwareSignalVO implements Serializable {

  private static final long serialVersionUID = 1L;

}
