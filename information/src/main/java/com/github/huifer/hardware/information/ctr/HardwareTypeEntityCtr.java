package com.github.huifer.hardware.information.ctr;

import com.github.huifer.hardware.common.base.PageAndSortRequest;
import com.github.huifer.hardware.common.base.PageResponse;
import com.github.huifer.hardware.common.base.ResultResponse;
import com.github.huifer.hardware.information.entity.HardwareTypeEntity;
import com.github.huifer.hardware.information.service.HardwareTypeEntityService;
import com.github.huifer.hardware.information.servlet.HardwareTypeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("hardware_type")
@RestController
public class HardwareTypeEntityCtr {

  private final HardwareTypeEntityService hardwareTypeEntityService;

  public HardwareTypeEntityCtr(HardwareTypeEntityService hardwareTypeEntityService) {
    this.hardwareTypeEntityService = hardwareTypeEntityService;
  }

  @PostMapping("/save")
  public ResultResponse<Boolean> save(@RequestBody HardwareTypeEntity entity) {
    hardwareTypeEntityService.save(entity);
    return ResultResponse.ok("ok");
  }

  @PostMapping("/delete/{id}")
  public ResultResponse<Boolean> delete(@PathVariable("id") String id) {
    hardwareTypeEntityService.delete(id);
    return ResultResponse.ok("ok");

  }

  @PostMapping("/update")
  public ResultResponse<Boolean> update(HardwareTypeEntity entity) {
    hardwareTypeEntityService.update(entity);
    return ResultResponse.ok("ok");
  }

  @GetMapping("/page")
  public ResultResponse<PageResponse<HardwareTypeEntity>> page(PageAndSortRequest page,
      HardwareTypeQuery query) {
    return ResultResponse.ok(hardwareTypeEntityService.page(page, query));
  }

  @GetMapping("/byId/{id}")
  public ResultResponse<HardwareTypeEntity> findById(
      @PathVariable("id") String id) {
    return ResultResponse.ok(hardwareTypeEntityService.findById(id));

  }
}
