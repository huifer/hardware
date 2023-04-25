package com.github.huifer.hardware.information.vo;


import com.github.huifer.hardware.common.base.PageAndSortRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.Data;

@Data
@Schema(description = "查询条件")
public class HardwareSignalMappingQueryVO extends PageAndSortRequest implements Serializable {

  private static final long serialVersionUID = 1L;




  /**
   * 硬件设备ID
   */
  @Schema(title = "硬件设备ID")
  private Long deviceId;


  /**
   * 硬件输出信号key
   */
  @Schema(title = "硬件输出信号key")
  private String signalKey;


  /**
   * 系统指定信号名称
   */
  @Schema(title = "信号名称")
  private String systemSignalName;



}
