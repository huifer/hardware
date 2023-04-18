package com.github.huifer.hardware.information.controller;


import com.github.huifer.hardware.information.dto.DeviceDetailDTO;
import com.github.huifer.hardware.information.service.DeviceDetailService;
import com.github.huifer.hardware.information.vo.DeviceDetailQueryVO;
import com.github.huifer.hardware.information.vo.DeviceDetailUpdateVO;
import com.github.huifer.hardware.information.vo.DeviceDetailVO;
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
@RequestMapping("/deviceDetail")
public class DeviceDetailController {

  @Autowired
  private DeviceDetailService deviceDetailService;

  @PostMapping
  public String save(@Valid @RequestBody DeviceDetailVO vO) {
    return deviceDetailService.save(vO).toString();
  }

  @DeleteMapping("/{id}")
  public void delete(@Valid @NotNull @PathVariable("id") Long id) {
    deviceDetailService.delete(id);
  }

  @PutMapping("/{id}")
  public void update(@Valid @NotNull @PathVariable("id") Long id,
      @Valid @RequestBody DeviceDetailUpdateVO vO) {
    deviceDetailService.update(id, vO);
  }

  @GetMapping("/{id}")
  public DeviceDetailDTO getById(@Valid @NotNull @PathVariable("id") Long id) {
    return deviceDetailService.getById(id);
  }

  @GetMapping
  public Page<DeviceDetailDTO> query(@Valid DeviceDetailQueryVO vO) {
    return deviceDetailService.query(vO);
  }
}
