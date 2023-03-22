package com.github.huifer.hardware.information.ctr;

import com.github.huifer.hardware.common.base.PageAndSortRequest;
import com.github.huifer.hardware.common.base.PageResponse;
import com.github.huifer.hardware.common.base.ResultResponse;
import com.github.huifer.hardware.information.entity.HardwareInfoEntity;
import com.github.huifer.hardware.information.service.HardwareInfoEntityService;
import com.github.huifer.hardware.information.servlet.HardwareInfoQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 硬件信息
 **/
@RequestMapping("hardware_info")
@RestController
@CrossOrigin
public class HardwareInfoEntityCtr {

  private final HardwareInfoEntityService hardwareInfoEntityService;

  public HardwareInfoEntityCtr(HardwareInfoEntityService hardwareInfoEntityService) {
    this.hardwareInfoEntityService = hardwareInfoEntityService;
  }


  @PostMapping("/save")
  public ResultResponse<Boolean> save(@RequestBody HardwareInfoEntity hardwareInfoEntity) {
    this.hardwareInfoEntityService.save(hardwareInfoEntity);
    return ResultResponse.ok();
  }

  @PostMapping("/update")
  public ResultResponse<Boolean> update(@RequestBody HardwareInfoEntity hardwareInfoEntity) {
    this.hardwareInfoEntityService.update(hardwareInfoEntity);
    return ResultResponse.ok();
  }

  @PostMapping("/delete/{id}")
  public ResultResponse<Boolean> delete(@PathVariable("id") String id) {
    this.hardwareInfoEntityService.delete(id);
    return ResultResponse.ok();
  }

  @GetMapping("/page")
  public ResultResponse<PageResponse<HardwareInfoEntity>> page(PageAndSortRequest page, HardwareInfoQuery query) {
    return ResultResponse.ok(hardwareInfoEntityService.page(
        page,
        query
    ));
  }

  @GetMapping("/byId/{id}")
  public ResultResponse<HardwareInfoEntity> byId(
     @PathVariable("id") String id) {

    return ResultResponse.ok(hardwareInfoEntityService.byId(id));
  }

}
