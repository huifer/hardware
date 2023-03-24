package com.github.huifer.hardware.information.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 硬件信号和硬件类型信号的对应关系
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@ToString
@Document
public class HardwareSignMappingEntity implements Serializable {

  @Id
  private String id;

  /**
   * 硬件唯一标识（系统的）
   * {@link HardwareInfoEntity#uid}
   **/
  private String hardwareUid;
  /**
   * 硬件类型id
   **/
  private String hardwareTypeId;

  private HardwareSignalEntity hardwareSignalEntity;
  /**
   * 硬件输出信号key
   **/
  private String fromKey;


  private LocalDateTime createTime;
  private boolean deleted;

}
