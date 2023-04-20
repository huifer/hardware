package com.github.huifer.hardware.information.vo;


import com.github.huifer.hardware.common.base.PageAndSortRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.Data;

@Data
@Schema(name = "设备信息查询")
public class HardwareDetailQueryVO extends PageAndSortRequest implements Serializable {

  private static final long serialVersionUID = 1L;


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

//
//  /**
//   * 设备所在经度
//   */
//  private Float latitude;
//
//
//  /**
//   * 设备所在纬度
//   */
//  private Float longitude;



}
