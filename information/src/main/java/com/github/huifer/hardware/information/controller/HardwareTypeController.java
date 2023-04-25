package com.github.huifer.hardware.information.controller;


import com.github.huifer.hardware.common.base.ResultResponse;
import com.github.huifer.hardware.information.service.HardwareTypeService;
import com.github.huifer.hardware.information.vo.HardwareTypeDTO;
import com.github.huifer.hardware.information.vo.HardwareTypeUpdateVO;
import com.github.huifer.hardware.information.vo.HardwareTypeVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
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

@Tag(name = "硬件类型")
@Validated
@RestController
@RequestMapping("/hardwareType")
public class HardwareTypeController {

  @Autowired
  private HardwareTypeService hardwareTypeService;

  @Operation(summary = "添加")
  @PostMapping
  public ResultResponse<Boolean> save(@Valid @RequestBody HardwareTypeVO vO) {
    return ResultResponse.ok(hardwareTypeService.save(vO).toString());
  }

  @Operation(summary = "删除")
  @DeleteMapping("/{id}")
  public ResultResponse<Boolean> delete(
      @Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id) {
    return ResultResponse.ok(hardwareTypeService.delete(id));
  }

  @Operation(summary = "修改")
  @PutMapping("/{id}")
  public ResultResponse<Boolean> update(
      @Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id,
      @Valid @RequestBody HardwareTypeUpdateVO vO) {
    return ResultResponse.ok(hardwareTypeService.update(id, vO));
  }

  @Operation(summary = "获取单个")
  @GetMapping("/{id}")
  public ResultResponse<HardwareTypeDTO> getById(
      @Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id) {
    return ResultResponse.ok(hardwareTypeService.getById(id));
  }

  @Operation(summary = "获取全部类型")
  @GetMapping("/list")
  public ResultResponse<List<HardwareTypeDTO>> query() {
    return ResultResponse.ok(hardwareTypeService.query());
  }
}
