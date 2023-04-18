package com.github.huifer.hardware.information.controller;


import com.github.huifer.hardware.information.dto.HardwareSignalDTO;
import com.github.huifer.hardware.information.service.HardwareSignalService;
import com.github.huifer.hardware.information.vo.HardwareSignalQueryVO;
import com.github.huifer.hardware.information.vo.HardwareSignalUpdateVO;
import com.github.huifer.hardware.information.vo.HardwareSignalVO;
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
@RequestMapping("/hardwareSignal")
public class HardwareSignalController {

  @Autowired
  private HardwareSignalService hardwareSignalService;

  @PostMapping
  public String save(@Valid @RequestBody HardwareSignalVO vO) {
    return hardwareSignalService.save(vO).toString();
  }

  @DeleteMapping("/{id}")
  public void delete(@Valid @NotNull @PathVariable("id") Long id) {
    hardwareSignalService.delete(id);
  }

  @PutMapping("/{id}")
  public void update(@Valid @NotNull @PathVariable("id") Long id,
      @Valid @RequestBody HardwareSignalUpdateVO vO) {
    hardwareSignalService.update(id, vO);
  }

  @GetMapping("/{id}")
  public HardwareSignalDTO getById(@Valid @NotNull @PathVariable("id") Long id) {
    return hardwareSignalService.getById(id);
  }

  @GetMapping
  public Page<HardwareSignalDTO> query(@Valid HardwareSignalQueryVO vO) {
    return hardwareSignalService.query(vO);
  }
}
