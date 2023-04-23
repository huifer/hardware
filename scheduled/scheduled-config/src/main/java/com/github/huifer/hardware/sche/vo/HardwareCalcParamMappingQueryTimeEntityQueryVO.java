package com.github.huifer.hardware.sche.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Schema(description = "自定义查询 公式所需要参数查询时间")
public class HardwareCalcParamMappingQueryTimeEntityQueryVO implements Serializable {

  private static final long serialVersionUID = 1L;


  /**
   * id
   */
  @Schema(description = "id")
  private Long id;


  /**
   * 所需参数
   */
  @Schema(description = "所需参数")
  private Long calcParamId;


  /**
   * 查询时间
   */
  @Schema(description = "查询时间")
  private LocalDateTime start;


  /**
   * 查询结束时间
   */
  @Schema(description = "查询结束时间")
  private LocalDateTime end;



}
