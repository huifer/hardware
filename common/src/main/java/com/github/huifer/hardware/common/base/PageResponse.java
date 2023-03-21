package com.github.huifer.hardware.common.base;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(title = "分页响应对象", description = "分页响应对象")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> {

  private long total;
  private long page;
  private long size;
  private List<T> data;


}
