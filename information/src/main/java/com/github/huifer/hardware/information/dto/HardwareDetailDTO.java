package com.github.huifer.hardware.information.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.Data;

@Data
@Schema(description = "设备信息")
public class HardwareDetailDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 设备详情表主键
   */
  @Schema(description = "id")
  private Long id;


  /**
   * 设备编号
   */
  @Schema(description = "设备编号")
  private String deviceNum;

  /**
   * 设备名称
   */
  @Schema(description = "设备名称")
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



}
