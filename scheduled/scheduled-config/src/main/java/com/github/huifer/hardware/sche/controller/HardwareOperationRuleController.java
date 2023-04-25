package com.github.huifer.hardware.sche.controller;

import com.github.huifer.hardware.sche.dto.HardwareOperationRuleEntityDTO;
import com.github.huifer.hardware.sche.service.HardwareOperationRuleEntityService;
import com.github.huifer.hardware.sche.vo.HardwareOperationRuleEntityQueryVO;
import com.github.huifer.hardware.sche.vo.HardwareOperationRuleEntityUpdateVO;
import com.github.huifer.hardware.sche.vo.HardwareOperationRuleEntityVO;
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

@Tag(name = "硬件计算规则")
@Validated
@RestController
@RequestMapping("/hardwareOperationRule")
public class HardwareOperationRuleController {

  @Autowired
  private HardwareOperationRuleEntityService hardwareOperationRuleService;

  @PostMapping
  @Operation(description = "保存 硬件计算规则")
  public String save(@Valid @RequestBody HardwareOperationRuleEntityVO vO) {
    return hardwareOperationRuleService.save(vO).toString();
  }

  @DeleteMapping("/{id}")
  @Operation(description = "删除 硬件计算规则")
  public void delete(@Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id) {
    hardwareOperationRuleService.delete(id);
  }

  @PutMapping("/{id}")
  @Operation(description = "更新 硬件计算规则")
  public void update(@Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id,
      @Valid @RequestBody HardwareOperationRuleEntityUpdateVO vO) {
    hardwareOperationRuleService.update(id, vO);
  }

  @GetMapping("/{id}")
  @Operation(description = "根据ID获取 硬件计算规则")
  public HardwareOperationRuleEntityDTO getById(
      @Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id) {
    return hardwareOperationRuleService.getById(id);
  }

  @GetMapping
  @Operation(description = "自定义查询 硬件计算规则")
  public Page<HardwareOperationRuleEntityDTO> query(@Valid HardwareOperationRuleEntityQueryVO vO) {
    return hardwareOperationRuleService.query(vO);
  }
}
