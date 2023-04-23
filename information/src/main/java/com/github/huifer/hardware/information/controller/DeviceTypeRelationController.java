package com.github.huifer.hardware.information.controller;


import com.github.huifer.hardware.common.base.ResultResponse;
import com.github.huifer.hardware.information.dto.DeviceTypeRelationDTO;
import com.github.huifer.hardware.information.service.DeviceTypeRelationService;
import com.github.huifer.hardware.information.vo.DeviceTypeRelationUpdateVO;
import com.github.huifer.hardware.information.vo.DeviceTypeRelationVO;
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

@Tag(name = "设备和类型关联管理")
@Validated
@RestController
@RequestMapping("/deviceTypeRelation")
public class DeviceTypeRelationController {


  @Autowired
  private DeviceTypeRelationService deviceTypeRelationService;

  @Operation(summary = "保存")
  @PostMapping
  public ResultResponse<Boolean> save(@Valid @RequestBody DeviceTypeRelationVO vO) {
    return ResultResponse.ok(deviceTypeRelationService.save(vO).toString());
  }

  @Operation(summary = "删除")
  @DeleteMapping("/{id}")
  public ResultResponse<Boolean> delete(
      @Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id) {
    return ResultResponse.ok(deviceTypeRelationService.delete(id));
  }

  @Operation(summary = "修改")
  @PutMapping("/{id}")
  public ResultResponse<Boolean> update(
      @Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id,
      @Valid @RequestBody DeviceTypeRelationUpdateVO vO) {
    return ResultResponse.ok(deviceTypeRelationService.update(id, vO));
  }

  @Operation(summary = "单个查询")
  @GetMapping("/{id}")
  public ResultResponse<DeviceTypeRelationDTO> getById(
      @Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id) {
    return ResultResponse.ok(deviceTypeRelationService.getById(id));
  }


}
