package com.github.huifer.hardware.sche.controller;

import com.github.huifer.hardware.sche.dto.HardwareComputingExecutorEntityDTO;
import com.github.huifer.hardware.sche.service.HardwareComputingExecutorEntityService;
import com.github.huifer.hardware.sche.vo.HardwareComputingExecutorEntityQueryVO;
import com.github.huifer.hardware.sche.vo.HardwareComputingExecutorEntityUpdateVO;
import com.github.huifer.hardware.sche.vo.HardwareComputingExecutorEntityVO;
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

@Tag(name = "硬件计算执行器")
@Validated
@RestController
@RequestMapping("/hardwareComputingExecutor")
public class HardwareComputingExecutorController {

  @Autowired
  private HardwareComputingExecutorEntityService hardwareComputingExecutorService;

  @PostMapping
  @Operation(description = "保存 硬件计算执行器")
  public String save(@Valid @RequestBody HardwareComputingExecutorEntityVO vO) {
    return hardwareComputingExecutorService.save(vO).toString();
  }

  @DeleteMapping("/{id}")
  @Operation(description = "删除 硬件计算执行器")
  public void delete(@Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id) {
    hardwareComputingExecutorService.delete(id);
  }

  @PutMapping("/{id}")
  @Operation(description = "更新 硬件计算执行器")
  public void update(@Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id,
      @Valid @RequestBody HardwareComputingExecutorEntityUpdateVO vO) {
    hardwareComputingExecutorService.update(id, vO);
  }

  @GetMapping("/{id}")
  @Operation(description = "根据ID获取 硬件计算执行器")
  public HardwareComputingExecutorEntityDTO getById(
      @Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id) {
    return hardwareComputingExecutorService.getById(id);
  }

  @GetMapping
  @Operation(description = "自定义查询 硬件计算执行器")
  public Page<HardwareComputingExecutorEntityDTO> query(
      @Valid HardwareComputingExecutorEntityQueryVO vO) {
    return hardwareComputingExecutorService.query(vO);
  }
}
