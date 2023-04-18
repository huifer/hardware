package com.github.huifer.hardware.information.controller;

import com.github.huifer.hardware.information.dto.HardwareTypeSignalDTO;
import com.github.huifer.hardware.information.service.HardwareTypeSignalService;

import com.github.huifer.hardware.information.vo.HardwareTypeSignalQueryVO;
import com.github.huifer.hardware.information.vo.HardwareTypeSignalUpdateVO;
import com.github.huifer.hardware.information.vo.HardwareTypeSignalVO;
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
@RequestMapping("/hardwareTypeSignal")
public class HardwareTypeSignalController {

  @Autowired
  private HardwareTypeSignalService hardwareTypeSignalService;

  @PostMapping
  public String save(@Valid @RequestBody HardwareTypeSignalVO vO) {
    return hardwareTypeSignalService.save(vO).toString();
  }

  @DeleteMapping("/{id}")
  public void delete(@Valid @NotNull @PathVariable("id") Long id) {
    hardwareTypeSignalService.delete(id);
  }

  @PutMapping("/{id}")
  public void update(@Valid @NotNull @PathVariable("id") Long id,
      @Valid @RequestBody HardwareTypeSignalUpdateVO vO) {
    hardwareTypeSignalService.update(id, vO);
  }

  @GetMapping("/{id}")
  public HardwareTypeSignalDTO getById(@Valid @NotNull @PathVariable("id") Long id) {
    return hardwareTypeSignalService.getById(id);
  }

  @GetMapping
  public Page<HardwareTypeSignalDTO> query(@Valid HardwareTypeSignalQueryVO vO) {
    return hardwareTypeSignalService.query(vO);
  }
}
