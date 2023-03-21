package com.github.huifer.hardware.information.service.impl;

import com.github.huifer.hardware.common.base.PageAndSortRequest;
import com.github.huifer.hardware.common.base.PageResponse;
import com.github.huifer.hardware.information.CustomerBeans;
import com.github.huifer.hardware.information.entity.HardwareInfoEntity;
import com.github.huifer.hardware.information.entity.HardwareInfoExtensionsEntity;
import com.github.huifer.hardware.information.entity.HardwareSignalEntity;
import com.github.huifer.hardware.information.entity.HardwareTypeEntity;
import com.github.huifer.hardware.information.service.HardwareInfoEntityService;
import com.github.huifer.hardware.information.service.HardwareSignMappingEntityService;
import com.github.huifer.hardware.information.service.HardwareTypeEntityService;
import com.github.huifer.hardware.information.servlet.HardwareTypeQuery;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = {CustomerBeans.class})
@ActiveProfiles(value = {"mongo", "logging"})
class HardwareInfoEntityServiceImplTest {

  @Autowired
  private HardwareInfoEntityService hardwareInfoEntityService;
  @Autowired
  private HardwareTypeEntityService hardwareTypeEntityService;

  @Test
  void saveType() {
    for (int i = 2; i < 13; i++) {
      HardwareTypeEntity entity = new HardwareTypeEntity();
      entity.setName("类型" + i);
      ArrayList<HardwareSignalEntity> hardwareSignalEntities = new ArrayList<>();
      HardwareSignalEntity e = new HardwareSignalEntity();
      e.setSysKey("sig_1");
      e.setName("信号1");
      e.setMin(BigDecimal.ZERO);
      e.setMax(BigDecimal.TEN);
      e.setDefaultWaringMin(BigDecimal.ZERO);
      e.setDefaultWaringMax(BigDecimal.TEN);
      hardwareSignalEntities.add(e);
      HardwareSignalEntity e1 = new HardwareSignalEntity();
      e1.setSysKey("sig_2");
      e1.setName("信号2");
      e1.setMin(BigDecimal.ZERO);
      e1.setMax(BigDecimal.TEN);
      e1.setDefaultWaringMin(BigDecimal.ZERO);
      e1.setDefaultWaringMax(BigDecimal.TEN);

      hardwareSignalEntities.add(e1);
      entity.setHardwareSignalEntities(hardwareSignalEntities);
      hardwareTypeEntityService.save(entity);

    }
  }

  @Autowired
  private HardwareSignMappingEntityService hardwareSignMappingEntityService;

  @Test
  void findBy() {
    HardwareTypeEntity e = this.hardwareTypeEntityService.findById("64190ed725cddb44b9f888aa");
    PageAndSortRequest page1 = new PageAndSortRequest();
    page1.setPage(0);
    page1.setSize(10);

    PageResponse<HardwareTypeEntity> page = this.hardwareTypeEntityService.page(page1,
        new HardwareTypeQuery());
    System.out.println();
  }

  @org.junit.jupiter.api.Test
  void save() {
    HardwareInfoEntity hardwareInfoEntity = new HardwareInfoEntity();
    hardwareInfoEntity.setDeviceName("设备名称1");
    hardwareInfoEntity.setAddress("设备地址1");
    hardwareInfoEntity.setCoordinates("设备坐标1");
    String uid = UUID.randomUUID().toString();
    hardwareInfoEntity.setUid(uid);
    ArrayList<HardwareInfoExtensionsEntity> extensionsEntities = new ArrayList<>();
    HardwareInfoExtensionsEntity e = new HardwareInfoExtensionsEntity();
    e.setUsing(true);
    e.setDeviceId("硬件供应商id");

    extensionsEntities.add(e);
    hardwareInfoEntity.setExtensionsEntities(extensionsEntities);
    hardwareInfoEntity.setStatus("使用");
    hardwareInfoEntity.setDeleted(false);

    hardwareInfoEntityService.save(hardwareInfoEntity);


  }

  @org.junit.jupiter.api.Test
  void update() {

  }

  @org.junit.jupiter.api.Test
  void delete() {
  }

  @org.junit.jupiter.api.Test
  void page() {
  }
}