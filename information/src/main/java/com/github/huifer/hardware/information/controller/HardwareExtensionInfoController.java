package com.github.huifer.hardware.information.controller;


import com.github.huifer.hardware.common.base.ResultResponse;
import com.github.huifer.hardware.information.dto.HardwareExtensionInfoDTO;
import com.github.huifer.hardware.information.service.HardwareExtensionInfoService;
import com.github.huifer.hardware.information.vo.HardwareExtensionInfoUpdateVO;
import com.github.huifer.hardware.information.vo.HardwareExtensionInfoVO;
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
@RequestMapping("/hardwareExtensionInfo")
public class HardwareExtensionInfoController {

  @Autowired
  private HardwareExtensionInfoService hardwareExtensionInfoService;

  @Operation(summary = "保存")
  @PostMapping
  public ResultResponse<String> save(@Valid @RequestBody HardwareExtensionInfoVO vO) {
    return ResultResponse.ok(hardwareExtensionInfoService.save(vO).toString());
  }

  @Operation(summary = "删除")
  @DeleteMapping("/{id}")
  public ResultResponse<Boolean> delete(
      @Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id) {
    return ResultResponse.ok(hardwareExtensionInfoService.delete(id));
  }

  @Operation(summary = "修改")
  @PutMapping("/{id}")
  public ResultResponse<Boolean> update(
      @Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id,
      @Valid @RequestBody HardwareExtensionInfoUpdateVO vO) {
    return ResultResponse.ok(hardwareExtensionInfoService.update(id, vO));
  }

  @Operation(summary = "单个查询")
  @GetMapping("/{id}")
  public ResultResponse<HardwareExtensionInfoDTO> getById(
      @Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id) {
    return ResultResponse.ok(hardwareExtensionInfoService.getById(id));
  }


}
