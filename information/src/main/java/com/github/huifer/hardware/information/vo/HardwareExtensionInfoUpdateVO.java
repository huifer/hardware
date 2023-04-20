package com.github.huifer.hardware.information.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class HardwareExtensionInfoUpdateVO implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 扩展信息
   */
  @NotNull(message = "扩展信息不能为空")
  @Schema(description = "扩展信息")
  private String extInfo;
}
