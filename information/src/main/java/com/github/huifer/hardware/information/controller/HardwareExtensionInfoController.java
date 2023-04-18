package com.github.huifer.hardware.information.controller;


import com.github.huifer.hardware.information.dto.HardwareExtensionInfoDTO;
import com.github.huifer.hardware.information.service.HardwareExtensionInfoService;
import com.github.huifer.hardware.information.vo.HardwareExtensionInfoQueryVO;
import com.github.huifer.hardware.information.vo.HardwareExtensionInfoUpdateVO;
import com.github.huifer.hardware.information.vo.HardwareExtensionInfoVO;
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

@Validated
@RestController
@RequestMapping("/hardwareExtensionInfo")
public class HardwareExtensionInfoController {

  @Autowired
  private HardwareExtensionInfoService hardwareExtensionInfoService;

  @PostMapping
  public String save(@Valid @RequestBody HardwareExtensionInfoVO vO) {
    return hardwareExtensionInfoService.save(vO).toString();
  }

  @DeleteMapping("/{id}")
  public void delete(@Valid @NotNull @PathVariable("id") Long id) {
    hardwareExtensionInfoService.delete(id);
  }

  @PutMapping("/{id}")
  public void update(@Valid @NotNull @PathVariable("id") Long id,
      @Valid @RequestBody HardwareExtensionInfoUpdateVO vO) {
    hardwareExtensionInfoService.update(id, vO);
  }

  @GetMapping("/{id}")
  public HardwareExtensionInfoDTO getById(@Valid @NotNull @PathVariable("id") Long id) {
    return hardwareExtensionInfoService.getById(id);
  }

  @GetMapping
  public Page<HardwareExtensionInfoDTO> query(@Valid HardwareExtensionInfoQueryVO vO) {
    return hardwareExtensionInfoService.query(vO);
  }
}
