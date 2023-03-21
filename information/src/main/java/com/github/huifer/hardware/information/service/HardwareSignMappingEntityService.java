package com.github.huifer.hardware.information.service;

import com.github.huifer.hardware.common.base.PageAndSortRequest;
import com.github.huifer.hardware.common.base.PageResponse;
import com.github.huifer.hardware.information.entity.HardwareSignMappingEntity;

public interface HardwareSignMappingEntityService {

  void save(HardwareSignMappingEntity entity);

  void delete(String id);

  PageResponse<HardwareSignMappingEntity> page(PageAndSortRequest request);

  void update(HardwareSignMappingEntity entity);


}
