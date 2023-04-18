package com.github.huifer.hardware.information.controller;


import com.github.huifer.hardware.information.service.HardwareTypeService;
import com.github.huifer.hardware.information.vo.HardwareTypeDTO;
import com.github.huifer.hardware.information.vo.HardwareTypeQueryVO;
import com.github.huifer.hardware.information.vo.HardwareTypeUpdateVO;
import com.github.huifer.hardware.information.vo.HardwareTypeVO;
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
@RequestMapping("/hardwareType")
public class HardwareTypeController {

  @Autowired
  private HardwareTypeService hardwareTypeService;

  @PostMapping
  public String save(@Valid @RequestBody HardwareTypeVO vO) {
    return hardwareTypeService.save(vO).toString();
  }

  @DeleteMapping("/{id}")
  public void delete(@Valid @NotNull @PathVariable("id") Long id) {
    hardwareTypeService.delete(id);
  }

  @PutMapping("/{id}")
  public void update(@Valid @NotNull @PathVariable("id") Long id,
      @Valid @RequestBody HardwareTypeUpdateVO vO) {
    hardwareTypeService.update(id, vO);
  }

  @GetMapping("/{id}")
  public HardwareTypeDTO getById(@Valid @NotNull @PathVariable("id") Long id) {
    return hardwareTypeService.getById(id);
  }

  @GetMapping
  public Page<HardwareTypeDTO> query(@Valid HardwareTypeQueryVO vO) {
    return hardwareTypeService.query(vO);
  }
}
