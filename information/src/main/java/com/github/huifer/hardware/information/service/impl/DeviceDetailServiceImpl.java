package com.github.huifer.hardware.information.service.impl;

import com.github.huifer.hardware.common.base.PageResponse;
import com.github.huifer.hardware.common.base.SortRequest;
import com.github.huifer.hardware.information.dto.DeviceDetailDTO;
import com.github.huifer.hardware.information.entity.BaseEntity;
import com.github.huifer.hardware.information.entity.DeviceDetailEntity;
import com.github.huifer.hardware.information.entity.DeviceDetailEntity.Fields;
import com.github.huifer.hardware.information.repository.HardwareDetailRepository;
import com.github.huifer.hardware.information.service.DeviceDetailService;
import com.github.huifer.hardware.information.vo.DeviceDetailQueryVO;
import com.github.huifer.hardware.information.vo.DeviceDetailUpdateVO;
import com.github.huifer.hardware.information.vo.DeviceDetailVO;
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
public class DeviceDetailServiceImpl implements DeviceDetailService {

  @Autowired
  private HardwareDetailRepository hardwareDetailRepository;


  @Transactional(rollbackFor = {Exception.class})
  public Long save(DeviceDetailVO vO) {
    DeviceDetailEntity bean = new DeviceDetailEntity();
    bean.setName(vO.getName());
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
    DeviceDetailEntity deviceDetail = requireOne(id);
    deviceDetail.setDeleted(true);
    deviceDetail.setUpdateTime(LocalDateTime.now());
    return hardwareDetailRepository.save(deviceDetail) != null;
  }


  @Transactional(rollbackFor = {Exception.class})
  public Boolean update(Long id, DeviceDetailUpdateVO vO) {
    DeviceDetailEntity bean = requireOne(id);
    List<DeviceDetailEntity> detailList = hardwareDetailRepository.findAll(
        (Specification<DeviceDetailEntity>) (root, query, cb) -> {
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


  public DeviceDetailDTO getById(Long id) {
    Optional<DeviceDetailEntity> byId = hardwareDetailRepository.findById(id);
    DeviceDetailEntity original = new DeviceDetailEntity();
    if (byId.isPresent()) {
      original = byId.get();
    }
    return toDTO(original);
  }

  public PageResponse<DeviceDetailDTO> query(DeviceDetailQueryVO vO) {
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
        (Specification<DeviceDetailEntity>) (root, query, cb) -> {
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
  private PageResponse<DeviceDetailDTO> dbToResp(Page<DeviceDetailEntity> all) {
    PageResponse<DeviceDetailDTO> response = new PageResponse<>();
    response.setTotal(all.getTotalElements());
    Pageable pageable = all.getPageable();
    response.setPage(pageable.getPageNumber());
    response.setSize(pageable.getPageSize());
    response.setData(all.getContent().stream().map(it -> {
      DeviceDetailDTO dto = new DeviceDetailDTO();
      BeanUtils.copyProperties(it, dto);
      return dto;
    }).toList());
    return response;
  }

  private DeviceDetailDTO toDTO(DeviceDetailEntity original) {
    DeviceDetailDTO bean = new DeviceDetailDTO();
    BeanUtils.copyProperties(original, bean);
    return bean;
  }

  private DeviceDetailEntity requireOne(Long id) {
    return hardwareDetailRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("此数据不存在请刷新: " + id));
  }
}
