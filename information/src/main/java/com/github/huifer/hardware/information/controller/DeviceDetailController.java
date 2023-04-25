package com.github.huifer.hardware.information.controller;


import com.github.huifer.hardware.common.base.PageResponse;
import com.github.huifer.hardware.common.base.ResultResponse;
import com.github.huifer.hardware.information.dto.DeviceDetailDTO;
import com.github.huifer.hardware.information.service.DeviceDetailService;
import com.github.huifer.hardware.information.vo.DeviceDetailQueryVO;
import com.github.huifer.hardware.information.vo.DeviceDetailUpdateVO;
import com.github.huifer.hardware.information.vo.DeviceDetailVO;
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

@Tag(name = "设备基本信息")
@Validated
@RestController
@RequestMapping("/hardwareDetail")
public class DeviceDetailController {

  @Autowired
  private DeviceDetailService hardwareDetailService;

  @Operation(summary = "保存")
  @PostMapping
  public ResultResponse<String> save(@Valid @RequestBody DeviceDetailVO vO) {
    return ResultResponse.ok(hardwareDetailService.save(vO).toString());
  }

  @Operation(summary = "删除")
  @DeleteMapping("/{id}")
  public ResultResponse<Boolean> delete(
      @Valid @NotNull(message = "设备id不能为空") @PathVariable("id") Long id) {
    return ResultResponse.ok(hardwareDetailService.delete(id));
  }

  @Operation(summary = "修改")
  @PutMapping("/{id}")
  public ResultResponse<Boolean> update(
      @Valid @NotNull(message = "设备id不能为空") @PathVariable("id") Long id,
      @Valid @RequestBody DeviceDetailUpdateVO vO) {
    return ResultResponse.ok(hardwareDetailService.update(id, vO));
  }

  @Operation(summary = "单个查询")
  @GetMapping("/{id}")
  public ResultResponse<DeviceDetailDTO> getById(
      @Valid @NotNull(message = "设备id不能为空") @PathVariable("id") Long id) {
    return ResultResponse.ok(hardwareDetailService.getById(id));
  }

  @Operation(summary = "分页")
  @PostMapping("/page")
  public ResultResponse<PageResponse<DeviceDetailDTO>> query(
      @RequestBody DeviceDetailQueryVO vO) {
    return ResultResponse.ok(hardwareDetailService.query(vO));
  }
}
