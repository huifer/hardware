package com.github.huifer.hardware.sche.controller;


import com.github.huifer.hardware.sche.dto.HardwareCalcParamMappingQueryTimeEntityDTO;
import com.github.huifer.hardware.sche.service.HardwareCalcParamMappingQueryTimeEntityService;
import com.github.huifer.hardware.sche.vo.HardwareCalcParamMappingQueryTimeEntityQueryVO;
import com.github.huifer.hardware.sche.vo.HardwareCalcParamMappingQueryTimeEntityUpdateVO;
import com.github.huifer.hardware.sche.vo.HardwareCalcParamMappingQueryTimeEntityVO;
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

@Tag(name = "公式所需要参数查询时间")
@Validated
@RestController
@RequestMapping("/hardwareCalcParamMappingQueryTime")
public class HardwareCalcParamMappingQueryTimeController {

  @Autowired
  private HardwareCalcParamMappingQueryTimeEntityService hardwareCalcParamMappingQueryTimeService;

  @PostMapping
  @Operation(description = "保存 公式所需要参数查询时间")
  public String save(@Valid @RequestBody HardwareCalcParamMappingQueryTimeEntityVO vO) {
    return hardwareCalcParamMappingQueryTimeService.save(vO).toString();
  }

  @DeleteMapping("/{id}")
  @Operation(description = "删除 公式所需要参数查询时间")
  public void delete(@Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id) {
    hardwareCalcParamMappingQueryTimeService.delete(id);
  }

  @PutMapping("/{id}")
  @Operation(description = "更新 公式所需要参数查询时间")
  public void update(@Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id,
      @Valid @RequestBody HardwareCalcParamMappingQueryTimeEntityUpdateVO vO) {
    hardwareCalcParamMappingQueryTimeService.update(id, vO);
  }

  @GetMapping("/{id}")
  @Operation(description = "根据ID获取 公式所需要参数查询时间")
  public HardwareCalcParamMappingQueryTimeEntityDTO getById(
      @Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id) {
    return hardwareCalcParamMappingQueryTimeService.getById(id);
  }

  @GetMapping
  @Operation(description = "自定义查询 公式所需要参数查询时间")
  public Page<HardwareCalcParamMappingQueryTimeEntityDTO> query(
      @Valid HardwareCalcParamMappingQueryTimeEntityQueryVO vO) {
    return hardwareCalcParamMappingQueryTimeService.query(vO);
  }
}
