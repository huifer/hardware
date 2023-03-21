package com.github.huifer.hardware.information.service;

import com.github.huifer.hardware.information.entity.HardwareInfoEntity;
import com.github.huifer.hardware.information.entity.HardwareTypeEntity;
import com.github.huifer.hardware.information.servlet.HardwareInfoQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HardwareInfoEntityService {


  void save(HardwareInfoEntity hardwareInfoEntity);

  void update(HardwareInfoEntity hardwareInfoEntity);

  void delete(String id);

  Page<HardwareInfoEntity> page(Pageable pageable, HardwareInfoQuery query);

}
