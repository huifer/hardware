package com.github.huifer.hardware.information.controller;


import com.github.huifer.hardware.common.base.PageResponse;
import com.github.huifer.hardware.common.base.ResultResponse;
import com.github.huifer.hardware.information.dto.HardwareSignalMappingDTO;
import com.github.huifer.hardware.information.service.HardwareSignalMappingService;
import com.github.huifer.hardware.information.vo.HardwareSignalMappingQueryVO;
import com.github.huifer.hardware.information.vo.HardwareSignalMappingUpdateVO;
import com.github.huifer.hardware.information.vo.HardwareSignalMappingVO;
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

@Tag(name = "硬件信号映射关系")
@Validated
@RestController
@RequestMapping("/hardwareSignalMapping")
public class HardwareSignalMappingController {

  @Autowired
  private HardwareSignalMappingService hardwareSignalMappingService;

  @Operation(summary = "添加")
  @PostMapping
  public ResultResponse<String> save(@Valid @RequestBody HardwareSignalMappingVO vO) {
    return ResultResponse.ok(hardwareSignalMappingService.save(vO).toString());
  }

  @Operation(summary = "删除")
  @DeleteMapping("/{id}")
  public ResultResponse<Boolean> delete(
      @Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id) {
    return ResultResponse.ok(hardwareSignalMappingService.delete(id));
  }

  @Operation(summary = "修改")
  @PutMapping("/{id}")
  public ResultResponse<Boolean> update(
      @Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id,
      @Valid @RequestBody HardwareSignalMappingUpdateVO vO) {
    return ResultResponse.ok(hardwareSignalMappingService.update(id, vO));
  }

  @Operation(summary = "获取单个")
  @GetMapping("/{id}")
  public ResultResponse<HardwareSignalMappingDTO> getById(
      @Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id) {
    return ResultResponse.ok(hardwareSignalMappingService.getById(id));
  }

  @Operation(summary = "分页")
  @PostMapping("/page")
  public ResultResponse<PageResponse<HardwareSignalMappingDTO>> query(
      @RequestBody HardwareSignalMappingQueryVO vO) {
    return ResultResponse.ok(hardwareSignalMappingService.query(vO));
  }
}
