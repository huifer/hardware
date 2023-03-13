package com.github.huifer.hardware.sc.influx;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@FieldNameConstants
public class SignalDocument {

  @Id
  private String id;

  @Indexed
  private String deviceType;
  @Indexed
  private String deviceId;

  @Indexed
  private String key;

  private String value;


  private long  time;


}
