package com.github.huifer.hardware.information.controller;


import com.github.huifer.hardware.information.dto.DeviceTypeRelationDTO;
import com.github.huifer.hardware.information.service.DeviceTypeRelationService;
import com.github.huifer.hardware.information.vo.DeviceTypeRelationQueryVO;
import com.github.huifer.hardware.information.vo.DeviceTypeRelationUpdateVO;
import com.github.huifer.hardware.information.vo.DeviceTypeRelationVO;
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
@RequestMapping("/deviceTypeRelation")
public class DeviceTypeRelationController {

  @Autowired
  private DeviceTypeRelationService deviceTypeRelationService;

  @PostMapping
  public String save(@Valid @RequestBody DeviceTypeRelationVO vO) {
    return deviceTypeRelationService.save(vO).toString();
  }

  @DeleteMapping("/{id}")
  public void delete(@Valid @NotNull @PathVariable("id") Long id) {
    deviceTypeRelationService.delete(id);
  }

  @PutMapping("/{id}")
  public void update(@Valid @NotNull @PathVariable("id") Long id,
      @Valid @RequestBody DeviceTypeRelationUpdateVO vO) {
    deviceTypeRelationService.update(id, vO);
  }

  @GetMapping("/{id}")
  public DeviceTypeRelationDTO getById(@Valid @NotNull @PathVariable("id") Long id) {
    return deviceTypeRelationService.getById(id);
  }

  @GetMapping
  public Page<DeviceTypeRelationDTO> query(@Valid DeviceTypeRelationQueryVO vO) {
    return deviceTypeRelationService.query(vO);
  }
}
