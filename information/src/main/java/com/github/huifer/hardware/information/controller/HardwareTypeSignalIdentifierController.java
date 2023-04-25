package com.github.huifer.hardware.information.controller;

import com.github.huifer.hardware.common.base.ResultResponse;
import com.github.huifer.hardware.information.dto.HardwareTypeSignalIdentifierDTO;
import com.github.huifer.hardware.information.service.HardwareTypeSignalIdentifierService;
import com.github.huifer.hardware.information.vo.HardwareTypeSignalIdentifierUpdateVO;
import com.github.huifer.hardware.information.vo.HardwareTypeSignalIdentifierVO;
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

@Tag(name = "硬件扩展信息")
@Validated
@RestController
@RequestMapping("/hardwareTypeSignalIdentifier")
public class HardwareTypeSignalIdentifierController {

  @Autowired
  private HardwareTypeSignalIdentifierService hardwareTypeSignalIdentifierService;

  @Operation(summary = "添加")
  @PostMapping
  public ResultResponse<Boolean> save(@Valid @RequestBody HardwareTypeSignalIdentifierVO vO) {
    return ResultResponse.ok(hardwareTypeSignalIdentifierService.save(vO).toString());
  }

  @Operation(summary = "删除")
  @DeleteMapping("/{id}")
  public ResultResponse<Boolean> delete(
      @Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id) {
    return ResultResponse.ok(hardwareTypeSignalIdentifierService.delete(id));
  }

  @Operation(summary = "修改")
  @PutMapping("/{id}")
  public ResultResponse<Boolean> update(
      @Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id,
      @Valid @RequestBody HardwareTypeSignalIdentifierUpdateVO vO) {
    return ResultResponse.ok(hardwareTypeSignalIdentifierService.update(id, vO));
  }

  @Operation(summary = "单个查询")
  @GetMapping("/{id}")
  public ResultResponse<HardwareTypeSignalIdentifierDTO> getById(
      @Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id) {
    return ResultResponse.ok(hardwareTypeSignalIdentifierService.getById(id));
  }


}
