package com.github.huifer.hardware.sche.event;


 import lombok.AllArgsConstructor;
 import lombok.Getter;
 import lombok.NoArgsConstructor;
 import lombok.EqualsAndHashCode;
 import lombok.Setter;
 import lombok.ToString;
 import lombok.experimental.FieldNameConstants;
/**
 * 数据提取完成
 **/
 @Getter
 @Setter
 @NoArgsConstructor
 @AllArgsConstructor
 @FieldNameConstants
 @EqualsAndHashCode
 @ToString
public class DataExtractionCompletedEvent {

  private String uid;
}
