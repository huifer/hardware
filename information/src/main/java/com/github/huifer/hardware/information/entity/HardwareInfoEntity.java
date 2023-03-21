package com.github.huifer.hardware.information.entity;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document("hardware_info")
public class HardwareInfoEntity implements Serializable {

  @Id
  private String id;

  /**
   * 设备名称
   **/
  private String deviceName;
  /**
   * 设备地址
   **/
  private String address;
  /**
   * 坐标
   **/
  private String coordinates;
  /**
   * 系统唯一id
   **/
  private String uid;

//  /**
//   * 设备类型
//   **/
//  private List<HardwareTypeEntity> hardwareTypeEntities;


  /**
   * 硬件扩展信息
   **/
  private List<HardwareInfoExtensionsEntity> extensionsEntities;


  /**
   * 状态
   */
  private String status;
  /**
   * 是否删除
   **/
  private boolean deleted;

}
