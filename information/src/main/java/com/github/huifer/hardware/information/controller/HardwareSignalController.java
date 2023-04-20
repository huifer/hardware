package com.github.huifer.hardware.information.controller;


import com.github.huifer.hardware.common.base.PageResponse;
import com.github.huifer.hardware.common.base.ResultResponse;
import com.github.huifer.hardware.information.dto.HardwareSignalDTO;
import com.github.huifer.hardware.information.service.HardwareSignalService;
import com.github.huifer.hardware.information.vo.HardwareSignalQueryVO;
import com.github.huifer.hardware.information.vo.HardwareSignalUpdateVO;
import com.github.huifer.hardware.information.vo.HardwareSignalVO;
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
@RequestMapping("/hardwareSignal")
public class HardwareSignalController {

  @Autowired
  private HardwareSignalService hardwareSignalService;

  @PostMapping
  public ResultResponse<String> save(@Valid @RequestBody HardwareSignalVO vO) {
    return ResultResponse.ok(hardwareSignalService.save(vO).toString());
  }

  @DeleteMapping("/{id}")
  public ResultResponse<Boolean> delete(
      @Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id) {
    return ResultResponse.ok(hardwareSignalService.delete(id));
  }

  @PutMapping("/{id}")
  public ResultResponse<Boolean> update(
      @Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id,
      @Valid @RequestBody HardwareSignalUpdateVO vO) {
    return ResultResponse.ok(hardwareSignalService.update(id, vO));
  }

  @GetMapping("/{id}")
  public ResultResponse<HardwareSignalDTO> getById(
      @Valid @NotNull(message = "id不能为空") @PathVariable("id") Long id) {
    return ResultResponse.ok(hardwareSignalService.getById(id));
  }

  @PostMapping("/page")
  public ResultResponse<PageResponse<HardwareSignalDTO>> query(@Valid HardwareSignalQueryVO vO) {
    return ResultResponse.ok(hardwareSignalService.query(vO));
  }
}
