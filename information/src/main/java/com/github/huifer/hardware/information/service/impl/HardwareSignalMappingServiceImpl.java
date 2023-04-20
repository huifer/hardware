package com.github.huifer.hardware.information.service.impl;

import com.github.huifer.hardware.common.base.PageResponse;
import com.github.huifer.hardware.common.base.SortRequest;
import com.github.huifer.hardware.information.dto.HardwareSignalMappingDTO;
import com.github.huifer.hardware.information.entity.BaseEntity;
import com.github.huifer.hardware.information.entity.HardwareSignalMapping;
import com.github.huifer.hardware.information.repository.HardwareSignalMappingRepository;
import com.github.huifer.hardware.information.service.HardwareSignalMappingService;
import com.github.huifer.hardware.information.vo.HardwareSignalMappingQueryVO;
import com.github.huifer.hardware.information.vo.HardwareSignalMappingUpdateVO;
import com.github.huifer.hardware.information.vo.HardwareSignalMappingVO;
import jakarta.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class HardwareSignalMappingServiceImpl implements HardwareSignalMappingService {

  @Autowired
  private HardwareSignalMappingRepository hardwareSignalMappingRepository;

  public Long save(HardwareSignalMappingVO vO) {
    HardwareSignalMapping bean = new HardwareSignalMapping();
    bean.setDeviceId(vO.getDeviceId());
    bean.setSignalKey(vO.getSignalKey());
    bean.setSystemSignalName(vO.getSystemSignalName());
    bean.setUpdateTime(LocalDateTime.now());
    bean.setCreateTime(LocalDateTime.now());
    bean.setDeleted(false);
    bean = hardwareSignalMappingRepository.save(bean);
    return bean.getId();
  }

  public Boolean delete(Long id) {
    HardwareSignalMapping hardwareSignalMapping = requireOne(id);
    hardwareSignalMapping.setDeleted(true);
    hardwareSignalMapping.setUpdateTime(LocalDateTime.now());
   return hardwareSignalMappingRepository.save(hardwareSignalMapping) != null;
  }

  public Boolean update(Long id, HardwareSignalMappingUpdateVO vO) {
    HardwareSignalMapping hardwareSignalMapping = requireOne(id);
    hardwareSignalMapping.setDeviceId(vO.getDeviceId());
    hardwareSignalMapping.setSignalKey(vO.getSignalKey());
    hardwareSignalMapping.setSystemSignalName(vO.getSystemSignalName());
    hardwareSignalMapping.setCreateTime(LocalDateTime.now());
    HardwareSignalMapping bean = requireOne(id);
    return hardwareSignalMappingRepository.save(bean) != null;
  }

  public HardwareSignalMappingDTO getById(Long id) {
    Optional<HardwareSignalMapping> byId = hardwareSignalMappingRepository.findById(id);
    HardwareSignalMapping original = new HardwareSignalMapping();
    if (byId.isPresent()){
      original = byId.get();
    }
    return toDTO(original);
  }

  public PageResponse<HardwareSignalMappingDTO> query(HardwareSignalMappingQueryVO vO) {
    Pageable of;
    SortRequest sort = vO.getSort();
    Sort by;
    if (sort != null) {
      by = Sort.by(sort.isDesc() ? Direction.DESC : Direction.ASC, sort.getSortKey());

    } else {
      by = Sort.by(Direction.DESC, "createTime");
    }
    of = PageRequest.of(vO.getPage(), vO.getSize(), by);

    return dbToResp(hardwareSignalMappingRepository.findAll(
        (Specification<HardwareSignalMapping>) (root, query, cb) -> {
          List<Predicate> list = new ArrayList<>();
          if (StringUtils.hasText(vO.getSystemSignalName())) {
            list.add(cb.like(root.get(HardwareSignalMapping.Fields.systemSignalName),
                "%" + vO.getSystemSignalName() + "%"));
          }
          if (vO.getDeviceId() != null) {
            list.add(cb.equal(root.get(HardwareSignalMapping.Fields.deviceId), vO.getDeviceId()));
          }
          if (StringUtils.hasText(vO.getSignalKey())) {
            list.add(cb.like(root.get(HardwareSignalMapping.Fields.signalKey),
                "%" + vO.getSignalKey() + "%"));
          }
          list.add(cb.equal(root.get(BaseEntity.Fields.deleted), true));
          return cb.and(list.toArray(Predicate[]::new));
        }, of));

  }
  private PageResponse<HardwareSignalMappingDTO> dbToResp(Page<HardwareSignalMapping> all) {
    PageResponse<HardwareSignalMappingDTO> response = new PageResponse<>();
    response.setTotal(all.getTotalElements());
    Pageable pageable = all.getPageable();
    response.setPage(pageable.getPageNumber());
    response.setSize(pageable.getPageSize());
    response.setData(all.getContent().stream().map(it -> {
      HardwareSignalMappingDTO dto = new HardwareSignalMappingDTO();
      BeanUtils.copyProperties(it, dto);
      return dto;
    }).toList());
    return response;
  }
  private HardwareSignalMappingDTO toDTO(HardwareSignalMapping original) {
    HardwareSignalMappingDTO bean = new HardwareSignalMappingDTO();
    BeanUtils.copyProperties(original, bean);
    return bean;
  }

  private HardwareSignalMapping requireOne(Long id) {
    return hardwareSignalMappingRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("此数据不存在请刷新: " + id));
  }
}
