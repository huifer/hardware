package com.github.huifer.hardware.information.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Data;


@Data
@Schema(description = "设备信息")
public class DeviceDetailVO implements Serializable {

  private static final long serialVersionUID = 1L;



  /**
   * 设备名称
   */
  @Schema(description = "设备名称")
  @NotNull(message = "设备名称不能为空")
  private String name;




  /**
   * 设备地址
   */
  @Schema(description = "设备地址")
  private String address;


  /**
   * 设备所在经度
   */
  @Schema(description = "设备所在经度")
  private Float latitude;


  /**
   * 设备所在纬度
   */
  @Schema(description = "设备所在纬度")
  private Float longitude;

  @NotNull(message = "硬件编号不能为空")
  @Schema(description = "硬件编号")
  private String deviceNum;

  @Schema(description = "启用停用")
  private Boolean state;

}
