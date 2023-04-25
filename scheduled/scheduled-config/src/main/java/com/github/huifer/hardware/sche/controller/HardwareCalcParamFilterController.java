package com.github.huifer.hardware.sche.controller;


import com.github.huifer.hardware.sche.dto.HardwareCalcParamFilterEntityDTO;
import com.github.huifer.hardware.sche.service.HardwareCalcParamFilterEntityService;
import com.github.huifer.hardware.sche.vo.HardwareCalcParamFilterEntityQueryVO;
import com.github.huifer.hardware.sche.vo.HardwareCalcParamFilterEntityUpdateVO;
import com.github.huifer.hardware.sche.vo.HardwareCalcParamFilterEntityVO;
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


@Tag(name = "公式参数过滤条件")
@Validated
@RestController
@RequestMapping("/hardwareCalcParamFilter")
public class HardwareCalcParamFilterController {

  @Autowired
  private HardwareCalcParamFilterEntityService hardwareCalcParamFilterService;

  @PostMapping
  @Operation(description = "保存 公式参数过滤条件")
  public String save(@Valid @RequestBody HardwareCalcParamFilterEntityVO vO) {
    return hardwareCalcParamFilterService.save(vO).toString();
  }

  @DeleteMapping("/{id}")
  @Operation(description = "删除 公式参数过滤条件")
  public void delete(@Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id) {
    hardwareCalcParamFilterService.delete(id);
  }

  @PutMapping("/{id}")
  @Operation(description = "更新 公式参数过滤条件")
  public void update(@Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id,
      @Valid @RequestBody HardwareCalcParamFilterEntityUpdateVO vO) {
    hardwareCalcParamFilterService.update(id, vO);
  }

  @GetMapping("/{id}")
  @Operation(description = "根据ID获取 公式参数过滤条件")
  public HardwareCalcParamFilterEntityDTO getById(
      @Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id) {
    return hardwareCalcParamFilterService.getById(id);
  }

  @GetMapping
  @Operation(description = "自定义查询 公式参数过滤条件")
  public Page<HardwareCalcParamFilterEntityDTO> query(
      @Valid HardwareCalcParamFilterEntityQueryVO vO) {
    return hardwareCalcParamFilterService.query(vO);
  }
}
