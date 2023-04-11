package com.github.huifer.hardware.information.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldNameConstants
@Document
public class HardwareTypeEntity implements Serializable {

  @Id
  private String id;
  /**
   * 硬件类型名称
   **/
  private String name;

  /**
   * 设备类型编码
   */
  private String code;
  /**
   * 硬件信号
   **/
  private List<HardwareSignalEntity> hardwareSignalEntities;

  private boolean deleted = false;

  private LocalDateTime createTime;

}
