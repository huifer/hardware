package com.github.huifer.hardware.information.service.impl;

import com.github.huifer.hardware.common.base.PageResponse;
import com.github.huifer.hardware.common.base.SortRequest;
import com.github.huifer.hardware.information.dto.HardwareSignalDTO;
import com.github.huifer.hardware.information.entity.BaseEntity;
import com.github.huifer.hardware.information.entity.HardwareSignal;
import com.github.huifer.hardware.information.entity.HardwareSignal.Fields;
import com.github.huifer.hardware.information.repository.HardwareSignalRepository;
import com.github.huifer.hardware.information.service.HardwareSignalService;
import com.github.huifer.hardware.information.vo.HardwareSignalQueryVO;
import com.github.huifer.hardware.information.vo.HardwareSignalUpdateVO;
import com.github.huifer.hardware.information.vo.HardwareSignalVO;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class HardwareSignalServiceImpl implements HardwareSignalService {

  @Autowired
  private HardwareSignalRepository hardwareSignalRepository;

  @Transactional(rollbackFor = {Exception.class})
  public Long save(HardwareSignalVO vO) {
    HardwareSignal bean = new HardwareSignal();
    bean.setName(vO.getName());
    bean.setMinValue(vO.getMinValue());
    bean.setMaxValue(vO.getMaxValue());
    bean.setDefaultWarnLow(vO.getDefaultWarnLow());
    bean.setDefaultWarnHigh(vO.getDefaultWarnHigh());
    bean.setState(vO.getState());
    bean.setUpdateTime(LocalDateTime.now());
    bean.setCreateTime(LocalDateTime.now());
    bean.setDeleted(false);
    bean = hardwareSignalRepository.save(bean);
    return bean.getId();
  }

  @Transactional(rollbackFor = {Exception.class})
  public Boolean delete(Long id) {
    HardwareSignal hardwareSignal = requireOne(id);
    hardwareSignal.setDeleted(true);
    hardwareSignal.setUpdateTime(LocalDateTime.now());
    return hardwareSignalRepository.save(hardwareSignal) != null;
  }


  @Transactional(rollbackFor = {Exception.class})
  public Boolean update(Long id, HardwareSignalUpdateVO vO) {
    HardwareSignal bean = requireOne(id);
    bean.setName(vO.getName());
    bean.setMinValue(vO.getMinValue());
    bean.setMaxValue(vO.getMaxValue());
    bean.setDefaultWarnLow(vO.getDefaultWarnLow());
    bean.setDefaultWarnHigh(vO.getDefaultWarnHigh());
    bean.setState(vO.getState());
    bean.setUpdateTime(LocalDateTime.now());
    return hardwareSignalRepository.save(bean) != null;
  }

  public HardwareSignalDTO getById(Long id) {
    Optional<HardwareSignal> byId = hardwareSignalRepository.findById(id);
    HardwareSignal original = new HardwareSignal();
    if (byId.isPresent()) {
      original = byId.get();
    }
    return toDTO(original);
  }

  public PageResponse<HardwareSignalDTO> query(HardwareSignalQueryVO vO) {
    Pageable of;
    SortRequest sort = vO.getSort();
    Sort by;
    if (sort != null) {
      by = Sort.by(sort.isDesc() ? Direction.DESC : Direction.ASC, sort.getSortKey());

    } else {
      by = Sort.by(Direction.DESC, "createTime");
    }
    of = PageRequest.of(vO.getPage(), vO.getSize(), by);

    return dbToResp(hardwareSignalRepository.findAll(
        (Specification<HardwareSignal>) (root, query, cb) -> {
          List<Predicate> list = new ArrayList<>();
          if (StringUtils.hasText(vO.getName())) {
            list.add(cb.like(root.get(Fields.name), "%" + vO.getName() + "%"));
          }
          if (vO.getMinValue() != null) {
            list.add(cb.equal(root.get(Fields.minValue), vO.getMinValue()));
          }
          if (vO.getMaxValue() != null) {
            list.add(cb.equal(root.get(Fields.maxValue), vO.getMaxValue()));
          }
          if (vO.getDefaultWarnHigh() != null) {
            list.add(cb.equal(root.get(Fields.defaultWarnHigh), vO.getDefaultWarnHigh()));
          }
          if (vO.getDefaultWarnLow() != null) {
            list.add(cb.equal(root.get(Fields.defaultWarnLow), vO.getDefaultWarnLow()));
          }
          list.add(cb.equal(root.get(BaseEntity.Fields.deleted), true));
          return cb.and(list.toArray(Predicate[]::new));
        }, of));

  }
  private PageResponse<HardwareSignalDTO> dbToResp(Page<HardwareSignal> all) {
    PageResponse<HardwareSignalDTO> response = new PageResponse<>();
    response.setTotal(all.getTotalElements());
    Pageable pageable = all.getPageable();
    response.setPage(pageable.getPageNumber());
    response.setSize(pageable.getPageSize());
    response.setData(all.getContent().stream().map(it -> {
      HardwareSignalDTO dto = new HardwareSignalDTO();
      BeanUtils.copyProperties(it, dto);
      return dto;
    }).toList());
    return response;
  }


  private HardwareSignalDTO toDTO(HardwareSignal original) {
    HardwareSignalDTO bean = new HardwareSignalDTO();
    BeanUtils.copyProperties(original, bean);
    return bean;
  }

  private HardwareSignal requireOne(Long id) {
    return hardwareSignalRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("此数据不存在请刷新: " + id));
  }
}
