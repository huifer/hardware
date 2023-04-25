package com.github.huifer.hardware.sche.controller;

import com.github.huifer.hardware.sche.dto.HardwareTaskExecutionRecordEntityDTO;
import com.github.huifer.hardware.sche.service.HardwareTaskExecutionRecordEntityService;
import com.github.huifer.hardware.sche.vo.HardwareTaskExecutionRecordEntityQueryVO;
import com.github.huifer.hardware.sche.vo.HardwareTaskExecutionRecordEntityUpdateVO;
import com.github.huifer.hardware.sche.vo.HardwareTaskExecutionRecordEntityVO;
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

@Tag(name = "定时任务执行记录表")
@Validated
@RestController
@RequestMapping("/hardwareTaskExecutionRecord")
public class HardwareTaskExecutionRecordController {

  @Autowired
  private HardwareTaskExecutionRecordEntityService hardwareTaskExecutionRecordService;

  @PostMapping
  @Operation(description = "保存 定时任务执行记录表")
  public String save(@Valid @RequestBody HardwareTaskExecutionRecordEntityVO vO) {
    return hardwareTaskExecutionRecordService.save(vO).toString();
  }

  @DeleteMapping("/{id}")
  @Operation(description = "删除 定时任务执行记录表")
  public void delete(@Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id) {
    hardwareTaskExecutionRecordService.delete(id);
  }

  @PutMapping("/{id}")
  @Operation(description = "更新 定时任务执行记录表")
  public void update(@Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id,
      @Valid @RequestBody HardwareTaskExecutionRecordEntityUpdateVO vO) {
    hardwareTaskExecutionRecordService.update(id, vO);
  }

  @GetMapping("/{id}")
  @Operation(description = "根据ID获取 定时任务执行记录表")
  public HardwareTaskExecutionRecordEntityDTO getById(
      @Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id) {
    return hardwareTaskExecutionRecordService.getById(id);
  }

  @GetMapping
  @Operation(description = "自定义查询 定时任务执行记录表")
  public Page<HardwareTaskExecutionRecordEntityDTO> query(
      @Valid HardwareTaskExecutionRecordEntityQueryVO vO) {
    return hardwareTaskExecutionRecordService.query(vO);
  }
}
