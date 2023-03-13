package com.github.huifer.hardware.sc.influx;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants
public class SignalQuery {

  private String deviceType;
  private String deviceId;
  private List<String> keys;


  /**
   * 单位: ms
   */
  private Long startTime;
  /**
   * 单位: ms
   */
  private Long endTime;
}
