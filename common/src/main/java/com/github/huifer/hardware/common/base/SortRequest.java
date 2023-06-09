package com.github.huifer.hardware.common.base;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Schema(description = "排序对象")
@Data
public class SortRequest {

  private String sortKey;
  private boolean desc;

}
