package com.github.huifer.hardware.information.service.impl;

import com.github.huifer.hardware.common.base.PageAndSortRequest;
import com.github.huifer.hardware.common.base.PageResponse;
import com.github.huifer.hardware.common.base.SortRequest;
import com.github.huifer.hardware.common.utils.GsonFactory;
import com.github.huifer.hardware.information.entity.HardwareTypeEntity;
import com.github.huifer.hardware.information.entity.HardwareTypeEntity.Fields;
import com.github.huifer.hardware.information.repository.HardwareTypeEntityRepository;
import com.github.huifer.hardware.information.service.HardwareTypeEntityService;
import com.github.huifer.hardware.information.servlet.HardwareTypeQuery;
import com.google.gson.Gson;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class HardwareTypeEntityServiceImpl implements HardwareTypeEntityService {

  private static final Logger logger = LoggerFactory.getLogger(HardwareTypeEntityServiceImpl.class);
  private final HardwareTypeEntityRepository hardwareTypeEntityRepository;
  private final MongoTemplate mongoTemplate;
  Gson gson = GsonFactory.getGson();

  public HardwareTypeEntityServiceImpl(HardwareTypeEntityRepository hardwareTypeEntityRepository,
      MongoTemplate mongoTemplate) {
    this.hardwareTypeEntityRepository = hardwareTypeEntityRepository;
    this.mongoTemplate = mongoTemplate;
  }

  @Transactional(rollbackFor = {Exception.class})
  @Override
  public void save(HardwareTypeEntity entity) {
    if (logger.isInfoEnabled()) {
      logger.info("save,entity = {}", gson.toJson(entity));
    }
    entity.setCreateTime(LocalDateTime.now());
    HardwareTypeEntity save = hardwareTypeEntityRepository.save(entity);
    System.out.println();
  }

  @Transactional(rollbackFor = {Exception.class})
  @Override
  public void delete(String id) {
    Optional<HardwareTypeEntity> byId = hardwareTypeEntityRepository.findById(id);
    if (byId.isPresent()) {
      HardwareTypeEntity hardwareTypeEntity = byId.get();
      hardwareTypeEntity.setDeleted(true);
      hardwareTypeEntityRepository.save(hardwareTypeEntity);
    }
  }

  @Transactional(rollbackFor = {Exception.class})
  @Override
  public void update(HardwareTypeEntity entity) {
    hardwareTypeEntityRepository.save(entity);
  }

  @Override
  public PageResponse<HardwareTypeEntity> page(PageAndSortRequest page, HardwareTypeQuery query) {
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

    if (!org.apache.commons.lang3.StringUtils.isEmpty(query.getName())) {
      query1.addCriteria(Criteria.where(Fields.name).regex(query.getName()));
    }
    //    query1.addCriteria(Criteria.where("hardwareSignalEntities.key").regex("sig_1"));
//    mongoTemplate.executeQuery(query1, HardwareTypeEntity.class, new DocumentCallbackHandler() {
//      @Override
//      public void processDocument(Document document) throws MongoException, DataAccessException {
//
//      }
//    });
    long count = mongoTemplate.count(query1, HardwareTypeEntity.class);
    query1.with(of);
    List<HardwareTypeEntity> hardwareTypeEntities = mongoTemplate.find(query1,
        HardwareTypeEntity.class);

    PageResponse<HardwareTypeEntity> pageResult = new PageResponse<>();
    pageResult.setTotal(count);
    pageResult.setPage(of.getPageNumber());
    pageResult.setSize(of.getPageSize());
    pageResult.setData(hardwareTypeEntities);

    return pageResult;
  }

  @Override
  public HardwareTypeEntity findById(String id) {
    return hardwareTypeEntityRepository.findById(id).orElse(null);
  }
}
