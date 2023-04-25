package com.github.huifer.hardware.information.service;

import com.github.huifer.hardware.common.base.PageAndSortRequest;
import com.github.huifer.hardware.common.base.PageResponse;
import com.github.huifer.hardware.information.entity.HardwareInfoEntity;
import com.github.huifer.hardware.information.servlet.HardwareInfoQuery;

public interface HardwareInfoEntityService {


  void save(HardwareInfoEntity hardwareInfoEntity);

  void update(HardwareInfoEntity hardwareInfoEntity);

  void delete(String id);

  PageResponse<HardwareInfoEntity> page(PageAndSortRequest page, HardwareInfoQuery query);

  HardwareInfoEntity byId(String id);
}
