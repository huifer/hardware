package com.github.huifer.hardware.information.service.impl;

import com.github.huifer.hardware.common.base.PageAndSortRequest;
import com.github.huifer.hardware.common.base.PageResponse;
import com.github.huifer.hardware.common.base.SortRequest;
import com.github.huifer.hardware.information.entity.HardwareInfoEntity;
import com.github.huifer.hardware.information.repository.HardwareInfoEntityRepository;
import com.github.huifer.hardware.information.service.HardwareInfoEntityService;
import com.github.huifer.hardware.information.servlet.HardwareInfoQuery;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HardwareInfoEntityServiceImpl implements HardwareInfoEntityService {

  @Autowired
  private MongoTemplate mongoTemplate;
  @Autowired
  private HardwareInfoEntityRepository hardwareInfoEntityRepository;

  @Transactional(rollbackFor = {Exception.class})
  @Override
  public void save(HardwareInfoEntity hardwareInfoEntity) {
    hardwareInfoEntity.setCreateTime(LocalDateTime.now());
    hardwareInfoEntity.setUid(UUID.randomUUID().toString());
    hardwareInfoEntityRepository.save(hardwareInfoEntity);
  }

  @Transactional(rollbackFor = {Exception.class})
  @Override
  public void update(HardwareInfoEntity hardwareInfoEntity) {
    hardwareInfoEntityRepository.save(hardwareInfoEntity);
  }

  @Transactional(rollbackFor = {Exception.class})
  @Override
  public void delete(String id) {
    Optional<HardwareInfoEntity> byId = hardwareInfoEntityRepository.findById(id);
    if (byId.isPresent()) {
      HardwareInfoEntity hardwareInfoEntity = byId.get();
      hardwareInfoEntity.setDeleted(true);
      hardwareInfoEntityRepository.save(hardwareInfoEntity);
    }
  }

  @Override
  public PageResponse<HardwareInfoEntity> page(PageAndSortRequest page, HardwareInfoQuery query) {

    Pageable of;
    SortRequest sort = page.getSort();
    if (sort != null) {
      Sort by = Sort.by(sort.isDesc() ? Direction.DESC : Direction.ASC, sort.getSortKey());
      of = PageRequest.of(page.getPage(), page.getSize(), by);

    } else {
      Sort by = Sort.by(Direction.DESC, "createTime");
      of = PageRequest.of(page.getPage(), page.getSize(), by);
    }
    Query query1 = new Query();
    query1.addCriteria(Criteria.where(HardwareInfoEntity.Fields.deleted).is(false));

    String deviceId = query.getDeviceId();
    if (!StringUtils.isEmpty(deviceId)) {
      query1.addCriteria(Criteria.where("extensionsEntities.deviceId").regex(deviceId));
    }
    String deviceName = query.getDeviceName();
    if (!StringUtils.isEmpty(deviceName)) {
      query1.addCriteria(Criteria.where(HardwareInfoEntity.Fields.deviceName).regex(deviceName));
    }
    String uid = query.getUid();
    if (!StringUtils.isEmpty(uid)) {
      query1.addCriteria(Criteria.where(HardwareInfoEntity.Fields.uid).regex(uid));

    }
    long count = mongoTemplate.count(query1, HardwareInfoEntity.class);
    query1.with(of);

    List<HardwareInfoEntity> hardwareSignMappingEntities = mongoTemplate.find(query1,
        HardwareInfoEntity.class);

    PageResponse<HardwareInfoEntity> pageResult = new PageResponse<>();
    pageResult.setTotal(count);
    pageResult.setPage(of.getPageNumber());
    pageResult.setSize(of.getPageSize());
    pageResult.setData(hardwareSignMappingEntities);

    return pageResult;
  }

  @Override
  public HardwareInfoEntity byId(String id) {
    return this.hardwareInfoEntityRepository.findById(id).orElse(null);
  }
}
