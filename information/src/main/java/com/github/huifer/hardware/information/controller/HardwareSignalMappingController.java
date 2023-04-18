package com.github.huifer.hardware.information.controller;



import com.github.huifer.hardware.information.dto.HardwareSignalMappingDTO;
import com.github.huifer.hardware.information.service.HardwareSignalMappingService;
import com.github.huifer.hardware.information.vo.HardwareSignalMappingQueryVO;
import com.github.huifer.hardware.information.vo.HardwareSignalMappingUpdateVO;
import com.github.huifer.hardware.information.vo.HardwareSignalMappingVO;
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
@RequestMapping("/hardwareSignalMapping")
public class HardwareSignalMappingController {

  @Autowired
  private HardwareSignalMappingService hardwareSignalMappingService;

  @PostMapping
  public String save(@Valid @RequestBody HardwareSignalMappingVO vO) {
    return hardwareSignalMappingService.save(vO).toString();
  }

  @DeleteMapping("/{id}")
  public void delete(@Valid @NotNull @PathVariable("id") Long id) {
    hardwareSignalMappingService.delete(id);
  }

  @PutMapping("/{id}")
  public void update(@Valid @NotNull @PathVariable("id") Long id,
      @Valid @RequestBody HardwareSignalMappingUpdateVO vO) {
    hardwareSignalMappingService.update(id, vO);
  }

  @GetMapping("/{id}")
  public HardwareSignalMappingDTO getById(@Valid @NotNull @PathVariable("id") Long id) {
    return hardwareSignalMappingService.getById(id);
  }

  @GetMapping
  public Page<HardwareSignalMappingDTO> query(@Valid HardwareSignalMappingQueryVO vO) {
    return hardwareSignalMappingService.query(vO);
  }
}
