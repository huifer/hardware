package com.github.huifer.hardware.information.service.impl;

import com.github.huifer.hardware.common.base.PageAndSortRequest;
import com.github.huifer.hardware.common.base.PageResponse;
import com.github.huifer.hardware.common.base.SortRequest;
import com.github.huifer.hardware.information.entity.HardwareSignMappingEntity;
import com.github.huifer.hardware.information.entity.HardwareTypeEntity.Fields;
import com.github.huifer.hardware.information.repository.HardwareSignMappingEntityRepository;
import com.github.huifer.hardware.information.service.HardwareSignMappingEntityService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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
public class HardwareSignMappingEntityServiceImpl implements HardwareSignMappingEntityService {

  private final MongoTemplate mongoTemplate;
  private final HardwareSignMappingEntityRepository repository;

  public HardwareSignMappingEntityServiceImpl(MongoTemplate mongoTemplate,
      HardwareSignMappingEntityRepository repository) {
    this.mongoTemplate = mongoTemplate;
    this.repository = repository;
  }

  @Transactional(rollbackFor = {Exception.class})
  @Override
  public void save(HardwareSignMappingEntity entity) {
    entity.setCreateTime(LocalDateTime.now());
    repository.save(entity);
  }

  @Transactional(rollbackFor = {Exception.class})
  @Override
  public void delete(String id) {
    Optional<HardwareSignMappingEntity> byId = repository.findById(id);
    if (byId.isPresent()) {
      HardwareSignMappingEntity hardwareSignMappingEntity = byId.get();
      hardwareSignMappingEntity.setDeleted(true);
      repository.save(hardwareSignMappingEntity);
    }
  }

  @Override
  public PageResponse<HardwareSignMappingEntity> page(PageAndSortRequest page) {
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
    query1.addCriteria(Criteria.where(Fields.deleted).is(false));
    long count = mongoTemplate.count(query1, HardwareSignMappingEntity.class);
    query1.with(of);

    List<HardwareSignMappingEntity> hardwareSignMappingEntities = mongoTemplate.find(query1,
        HardwareSignMappingEntity.class);

    PageResponse<HardwareSignMappingEntity> pageResult = new PageResponse<>();
    pageResult.setTotal(count);
    pageResult.setPage(of.getPageNumber());
    pageResult.setSize(of.getPageSize());
    pageResult.setData(hardwareSignMappingEntities);

    return pageResult;
  }

  @Transactional(rollbackFor = {Exception.class})
  @Override
  public void update(HardwareSignMappingEntity entity) {
    repository.save(entity);
  }
}
