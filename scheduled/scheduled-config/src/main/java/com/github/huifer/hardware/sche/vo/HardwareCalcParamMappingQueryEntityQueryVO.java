package com.github.huifer.hardware.sche.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "自定义查询 公式所需要参数")
public class HardwareCalcParamMappingQueryEntityQueryVO implements java.io.Serializable {

  private static final long serialVersionUID = 1L;



  /**
   * 公式参数id
   */
  @Schema(description = "公式参数id")
  private java.lang.Long calcParamId;

  /**
   * 设备ID
   */
  @Schema(description = "设备ID")
  private java.lang.Long deviceId;

  /**
   * 设备类型ID
   */
  @Schema(description = "设备类型ID")
  private java.lang.Long deviceTypeId;


}
