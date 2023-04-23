package com.github.huifer.hardware.sche.controller;

import com.github.huifer.hardware.sche.dto.HardwareComputingExecutorResultEntityDTO;
import com.github.huifer.hardware.sche.service.HardwareComputingExecutorResultEntityService;
import com.github.huifer.hardware.sche.vo.HardwareComputingExecutorResultEntityQueryVO;
import com.github.huifer.hardware.sche.vo.HardwareComputingExecutorResultEntityUpdateVO;
import com.github.huifer.hardware.sche.vo.HardwareComputingExecutorResultEntityVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "硬件计算执行器执行结果")
@Validated
@RestController
@RequestMapping("/hardwareComputingExecutorResult")
public class HardwareComputingExecutorResultController {

  @Autowired
  private HardwareComputingExecutorResultEntityService hardwareComputingExecutorResultService;

  @PostMapping
  @Operation(description = "保存 硬件计算执行器执行结果")
  public String save(@Valid @RequestBody HardwareComputingExecutorResultEntityVO vO) {
    return hardwareComputingExecutorResultService.save(vO).toString();
  }

  @DeleteMapping("/{id}")
  @Operation(description = "删除 硬件计算执行器执行结果")
  public void delete(@Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id) {
    hardwareComputingExecutorResultService.delete(id);
  }

  @PutMapping("/{id}")
  @Operation(description = "更新 硬件计算执行器执行结果")
  public void update(@Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id,
      @Valid @RequestBody HardwareComputingExecutorResultEntityUpdateVO vO) {
    hardwareComputingExecutorResultService.update(id, vO);
  }

  @GetMapping("/{id}")
  @Operation(description = "根据ID获取 硬件计算执行器执行结果")
  public HardwareComputingExecutorResultEntityDTO getById(
      @Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id) {
    return hardwareComputingExecutorResultService.getById(id);
  }

  @GetMapping
  @Operation(description = "自定义查询 硬件计算执行器执行结果")
  public Page<HardwareComputingExecutorResultEntityDTO> query(
      @Valid HardwareComputingExecutorResultEntityQueryVO vO) {
    return hardwareComputingExecutorResultService.query(vO);
  }
}
