package com.github.huifer.hardware.information.controller;

import com.github.huifer.hardware.common.base.ResultResponse;
import com.github.huifer.hardware.information.dto.HardwareTypeSignalDTO;
import com.github.huifer.hardware.information.service.HardwareTypeSignalService;
import com.github.huifer.hardware.information.vo.HardwareTypeSignalUpdateVO;
import com.github.huifer.hardware.information.vo.HardwareTypeSignalVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "类型和信号绑定关系")
@Validated
@RestController
@RequestMapping("/hardwareTypeSignal")
public class HardwareTypeSignalController {

  @Autowired
  private HardwareTypeSignalService hardwareTypeSignalService;

  @Operation(summary = "保存")
  @PostMapping
  public ResultResponse<Boolean> save(@Valid @RequestBody HardwareTypeSignalVO vO) {
    return ResultResponse.ok(hardwareTypeSignalService.save(vO).toString());
  }

  @Operation(summary = "删除")
  @DeleteMapping("/{id}")
  public ResultResponse<Boolean> delete(
      @Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id) {
    return ResultResponse.ok(hardwareTypeSignalService.delete(id));
  }

  @Operation(summary = "修改")
  @PutMapping("/{id}")
  public ResultResponse<Boolean> update(
      @Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id,
      @Valid @RequestBody HardwareTypeSignalUpdateVO vO) {
    return ResultResponse.ok(hardwareTypeSignalService.update(id, vO));
  }

  @Operation(summary = "单个查询")
  @GetMapping("/{id}")
  public ResultResponse<HardwareTypeSignalDTO> getById(
      @Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id) {
    return ResultResponse.ok(hardwareTypeSignalService.getById(id));
  }

}
