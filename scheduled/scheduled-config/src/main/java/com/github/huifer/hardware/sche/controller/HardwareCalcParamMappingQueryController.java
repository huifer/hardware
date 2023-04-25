package com.github.huifer.hardware.sche.controller;


import com.github.huifer.hardware.sche.dto.HardwareCalcParamMappingQueryEntityDTO;
import com.github.huifer.hardware.sche.service.HardwareCalcParamMappingQueryEntityService;
import com.github.huifer.hardware.sche.vo.HardwareCalcParamMappingQueryEntityQueryVO;
import com.github.huifer.hardware.sche.vo.HardwareCalcParamMappingQueryEntityUpdateVO;
import com.github.huifer.hardware.sche.vo.HardwareCalcParamMappingQueryEntityVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "公式所需要参数")
@Validated
@RestController
@RequestMapping("/hardwareCalcParamMappingQuery")
public class HardwareCalcParamMappingQueryController {

  @Autowired
  private HardwareCalcParamMappingQueryEntityService hardwareCalcParamMappingQueryService;

  @PostMapping
  @Operation(description = "保存 公式所需要参数")
  public String save(@Valid @RequestBody HardwareCalcParamMappingQueryEntityVO vO) {
    return hardwareCalcParamMappingQueryService.save(vO).toString();
  }

  @DeleteMapping("/{id}")
  @Operation(description = "删除 公式所需要参数")
  public void delete(@Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id) {
    hardwareCalcParamMappingQueryService.delete(id);
  }

  @PutMapping("/{id}")
  @Operation(description = "更新 公式所需要参数")
  public void update(@Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id,
      @Valid @RequestBody HardwareCalcParamMappingQueryEntityUpdateVO vO) {
    hardwareCalcParamMappingQueryService.update(id, vO);
  }

  @GetMapping("/{id}")
  @Operation(description = "根据ID获取 公式所需要参数")
  public HardwareCalcParamMappingQueryEntityDTO getById(
      @Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id) {
    return hardwareCalcParamMappingQueryService.getById(id);
  }

  @GetMapping
  @Operation(description = "自定义查询 公式所需要参数")
  public Page<HardwareCalcParamMappingQueryEntityDTO> query(
      @Valid HardwareCalcParamMappingQueryEntityQueryVO vO) {
    return hardwareCalcParamMappingQueryService.query(vO);
  }
}
