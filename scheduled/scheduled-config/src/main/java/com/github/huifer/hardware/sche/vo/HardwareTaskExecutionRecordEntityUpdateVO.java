package com.github.huifer.hardware.sche.vo;


import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Schema(description = "更新 定时任务执行记录表")
@EqualsAndHashCode(callSuper = false)
public class HardwareTaskExecutionRecordEntityUpdateVO extends
    HardwareTaskExecutionRecordEntityVO implements Serializable {

  private static final long serialVersionUID = 1L;

}
