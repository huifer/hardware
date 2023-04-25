package com.github.huifer.hardware.sche.controller;

import com.github.huifer.hardware.sche.dto.HardwareCalcParamsEntityDTO;
import com.github.huifer.hardware.sche.service.HardwareCalcParamsEntityService;
import com.github.huifer.hardware.sche.vo.HardwareCalcParamsEntityQueryVO;
import com.github.huifer.hardware.sche.vo.HardwareCalcParamsEntityUpdateVO;
import com.github.huifer.hardware.sche.vo.HardwareCalcParamsEntityVO;
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

@Tag(name = "公式所需参数")
@Validated
@RestController
@RequestMapping("/hardwareCalcParams")
public class HardwareCalcParamsController {

  @Autowired
  private HardwareCalcParamsEntityService hardwareCalcParamsService;

  @PostMapping
  @Operation(description = "保存 公式所需参数")
  public String save(@Valid @RequestBody HardwareCalcParamsEntityVO vO) {
    return hardwareCalcParamsService.save(vO).toString();
  }

  @DeleteMapping("/{id}")
  @Operation(description = "删除 公式所需参数")
  public void delete(@Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id) {
    hardwareCalcParamsService.delete(id);
  }

  @PutMapping("/{id}")
  @Operation(description = "更新 公式所需参数")
  public void update(@Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id,
      @Valid @RequestBody HardwareCalcParamsEntityUpdateVO vO) {
    hardwareCalcParamsService.update(id, vO);
  }

  @GetMapping("/{id}")
  @Operation(description = "根据ID获取 公式所需参数")
  public HardwareCalcParamsEntityDTO getById(
      @Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id) {
    return hardwareCalcParamsService.getById(id);
  }

  @GetMapping
  @Operation(description = "自定义查询 公式所需参数")
  public Page<HardwareCalcParamsEntityDTO> query(@Valid HardwareCalcParamsEntityQueryVO vO) {
    return hardwareCalcParamsService.query(vO);
  }
}
