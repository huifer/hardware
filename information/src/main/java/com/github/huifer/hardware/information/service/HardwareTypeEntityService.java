package com.github.huifer.hardware.information.service;

import com.github.huifer.hardware.common.base.PageAndSortRequest;
import com.github.huifer.hardware.common.base.PageResponse;
import com.github.huifer.hardware.information.entity.HardwareTypeEntity;
import com.github.huifer.hardware.information.servlet.HardwareTypeQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HardwareTypeEntityService {

  void save(HardwareTypeEntity entity);

  void delete(String id);

  void update(HardwareTypeEntity entity);

  PageResponse<HardwareTypeEntity> page(PageAndSortRequest page , HardwareTypeQuery query);

  HardwareTypeEntity findById(String id);
}
