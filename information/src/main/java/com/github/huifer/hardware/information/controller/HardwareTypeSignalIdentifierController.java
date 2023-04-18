package com.github.huifer.hardware.information.controller;

import com.github.huifer.hardware.information.dto.HardwareTypeSignalIdentifierDTO;
import com.github.huifer.hardware.information.service.HardwareTypeSignalIdentifierService;
import com.github.huifer.hardware.information.vo.HardwareTypeSignalIdentifierQueryVO;
import com.github.huifer.hardware.information.vo.HardwareTypeSignalIdentifierUpdateVO;
import com.github.huifer.hardware.information.vo.HardwareTypeSignalIdentifierVO;
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
@RequestMapping("/hardwareTypeSignalIdentifier")
public class HardwareTypeSignalIdentifierController {

  @Autowired
  private HardwareTypeSignalIdentifierService hardwareTypeSignalIdentifierService;

  @PostMapping
  public String save(@Valid @RequestBody HardwareTypeSignalIdentifierVO vO) {
    return hardwareTypeSignalIdentifierService.save(vO).toString();
  }

  @DeleteMapping("/{id}")
  public void delete(@Valid @NotNull @PathVariable("id") Long id) {
    hardwareTypeSignalIdentifierService.delete(id);
  }

  @PutMapping("/{id}")
  public void update(@Valid @NotNull @PathVariable("id") Long id,
      @Valid @RequestBody HardwareTypeSignalIdentifierUpdateVO vO) {
    hardwareTypeSignalIdentifierService.update(id, vO);
  }

  @GetMapping("/{id}")
  public HardwareTypeSignalIdentifierDTO getById(@Valid @NotNull @PathVariable("id") Long id) {
    return hardwareTypeSignalIdentifierService.getById(id);
  }

  @GetMapping
  public Page<HardwareTypeSignalIdentifierDTO> query(
      @Valid HardwareTypeSignalIdentifierQueryVO vO) {
    return hardwareTypeSignalIdentifierService.query(vO);
  }
}
