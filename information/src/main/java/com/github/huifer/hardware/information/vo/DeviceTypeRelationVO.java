package com.github.huifer.hardware.information.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.Data;


@Data
@Schema(description = "硬件类型和设备关系")
public class DeviceTypeRelationVO implements Serializable {

  private static final long serialVersionUID = 1L;


  /**
   * 设备详情表中的设备ID
   */
  @Schema(description = "设备ID")
  private Long deviceId;


  /**
   * 硬件类型表中的硬件类型ID
   */
  @Schema(description = "硬件类型id")
  private Long typeId;



}
