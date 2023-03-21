package com.github.huifer.hardware.information.service.impl;

import com.github.huifer.hardware.information.entity.HardwareInfoEntity;
import com.github.huifer.hardware.information.entity.HardwareTypeEntity;
import com.github.huifer.hardware.information.service.HardwareInfoEntityService;
import com.github.huifer.hardware.information.servlet.HardwareInfoQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HardwareInfoEntityServiceImpl implements HardwareInfoEntityService {

  @Transactional(rollbackFor = {Exception.class})
  @Override
  public void save(HardwareInfoEntity hardwareInfoEntity) {

  }

  @Transactional(rollbackFor = {Exception.class})
  @Override
  public void update(HardwareInfoEntity hardwareInfoEntity) {

  }

  @Transactional(rollbackFor = {Exception.class})
  @Override
  public void delete(String id) {

  }

  @Override
  public Page<HardwareInfoEntity> page(Pageable pageable, HardwareInfoQuery query) {
    return null;
  }
}
