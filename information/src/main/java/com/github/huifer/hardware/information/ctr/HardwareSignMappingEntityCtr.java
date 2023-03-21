package com.github.huifer.hardware.information.ctr;

import com.github.huifer.hardware.common.base.PageAndSortRequest;
import com.github.huifer.hardware.common.base.PageResponse;
import com.github.huifer.hardware.common.base.ResultResponse;
import com.github.huifer.hardware.information.entity.HardwareSignMappingEntity;
import com.github.huifer.hardware.information.service.HardwareSignMappingEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("hardware_sign_mapping")
@RestController
public class HardwareSignMappingEntityCtr {

  private final HardwareSignMappingEntityService hardwareSignMappingEntityService;

  public HardwareSignMappingEntityCtr(
      HardwareSignMappingEntityService hardwareSignMappingEntityService) {
    this.hardwareSignMappingEntityService = hardwareSignMappingEntityService;
  }

  @PostMapping("/save")
  public ResultResponse<Boolean> save(
      @RequestBody HardwareSignMappingEntity entity) {
    hardwareSignMappingEntityService.save(entity);
    return ResultResponse.ok();
  }

  @PostMapping("/delete/{id}")
  public ResultResponse<Boolean> delete(
      @PathVariable("id") String id) {
    hardwareSignMappingEntityService.delete(id);
    return ResultResponse.ok();
  }

  @GetMapping("/page")
  public ResultResponse<PageResponse<HardwareSignMappingEntity>> page(PageAndSortRequest request) {
    return ResultResponse.ok(hardwareSignMappingEntityService.page(request));
  }

  @PostMapping("/update")
  public ResultResponse<Boolean> update(@RequestBody HardwareSignMappingEntity entity) {
    this.hardwareSignMappingEntityService.update(entity);
    return ResultResponse.ok();

  }


}
