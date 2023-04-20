package com.github.huifer.hardware.information.service.impl;

import com.github.huifer.hardware.common.base.PageResponse;
import com.github.huifer.hardware.common.base.SortRequest;
import com.github.huifer.hardware.information.dto.HardwareDetailDTO;
import com.github.huifer.hardware.information.entity.BaseEntity;
import com.github.huifer.hardware.information.entity.HardwareDetail;
import com.github.huifer.hardware.information.entity.HardwareDetail.Fields;
import com.github.huifer.hardware.information.repository.HardwareDetailRepository;
import com.github.huifer.hardware.information.service.HardwareDetailService;
import com.github.huifer.hardware.information.vo.HardwareDetailQueryVO;
import com.github.huifer.hardware.information.vo.HardwareDetailUpdateVO;
import com.github.huifer.hardware.information.vo.HardwareDetailVO;
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
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;


@Service
public class HardwareDetailServiceImpl implements HardwareDetailService {

  @Autowired
  private HardwareDetailRepository hardwareDetailRepository;


  @Transactional(rollbackFor = {Exception.class})
  public Long save(HardwareDetailVO vO) {
    HardwareDetail bean = new HardwareDetail();
    bean.setName(vO.getName());
    bean.setDeviceNum(vO.getDeviceNum());
    bean.setAddress(vO.getAddress());
    bean.setLatitude(vO.getLatitude());
    bean.setLongitude(vO.getLongitude());
    bean.setCreateTime(LocalDateTime.now());
    bean.setState(vO.getState());
    bean.setDeleted(false);
    bean = hardwareDetailRepository.save(bean);
    return bean.getId();
  }

  @Transactional(rollbackFor = {Exception.class})
  public Boolean delete(Long id) {
    HardwareDetail deviceDetail = requireOne(id);
    deviceDetail.setDeleted(true);
    deviceDetail.setUpdateTime(LocalDateTime.now());
    return hardwareDetailRepository.save(deviceDetail) != null;
  }


  @Transactional(rollbackFor = {Exception.class})
  public Boolean update(Long id, HardwareDetailUpdateVO vO) {
    HardwareDetail bean = requireOne(id);
    List<HardwareDetail> detailList = hardwareDetailRepository.findAll(
        (Specification<HardwareDetail>) (root, query, cb) -> {
          List<Predicate> list = new ArrayList<>();
          list.add(cb.equal(root.get(Fields.name), vO.getName()));
          list.add(cb.notEqual(root.get(Fields.id), id));
          return cb.and(list.toArray(Predicate[]::new));
        });
    if (!CollectionUtils.isEmpty(detailList)) {
      throw new RuntimeException("已经存在该设备名称！");
    }
    bean.setName(vO.getName());
    bean.setAddress(vO.getAddress());
    bean.setLatitude(vO.getLatitude());
    bean.setLongitude(vO.getLongitude());
    bean.setUpdateTime(LocalDateTime.now());
    bean.setState(vO.getState());
    return hardwareDetailRepository.save(bean) != null;
  }


  public HardwareDetailDTO getById(Long id) {
    Optional<HardwareDetail> byId = hardwareDetailRepository.findById(id);
    HardwareDetail original = new HardwareDetail();
    if (byId.isPresent()) {
      original = byId.get();
    }
    return toDTO(original);
  }

  public PageResponse<HardwareDetailDTO> query(HardwareDetailQueryVO vO) {
    Pageable of;
    SortRequest sort = vO.getSort();
    Sort by;
    if (sort != null) {
      by = Sort.by(sort.isDesc() ? Direction.DESC : Direction.ASC, sort.getSortKey());

    } else {
      by = Sort.by(Direction.DESC, "createTime");
    }
    of = PageRequest.of(vO.getPage(), vO.getSize(), by);

    return dbToResp(hardwareDetailRepository.findAll(
        (Specification<HardwareDetail>) (root, query, cb) -> {
          List<Predicate> list = new ArrayList<>();
          if (StringUtils.hasText(vO.getName())) {
            list.add(cb.like(root.get(Fields.name), "%" + vO.getName() + "%"));
          }
          if (StringUtils.hasText(vO.getAddress())) {
            list.add(cb.like(root.get(Fields.address), "%" + vO.getAddress() + "%"));
          }
          list.add(cb.equal(root.get(BaseEntity.Fields.deleted), true));
          return cb.and(list.toArray(Predicate[]::new));
        }, of));

  }
  private PageResponse<HardwareDetailDTO> dbToResp(Page<HardwareDetail> all) {
    PageResponse<HardwareDetailDTO> response = new PageResponse<>();
    response.setTotal(all.getTotalElements());
    Pageable pageable = all.getPageable();
    response.setPage(pageable.getPageNumber());
    response.setSize(pageable.getPageSize());
    response.setData(all.getContent().stream().map(it -> {
      HardwareDetailDTO dto = new HardwareDetailDTO();
      BeanUtils.copyProperties(it, dto);
      return dto;
    }).toList());
    return response;
  }

  private HardwareDetailDTO toDTO(HardwareDetail original) {
    HardwareDetailDTO bean = new HardwareDetailDTO();
    BeanUtils.copyProperties(original, bean);
    return bean;
  }

  private HardwareDetail requireOne(Long id) {
    return hardwareDetailRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("此数据不存在请刷新: " + id));
  }
}
