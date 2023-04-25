package com.github.huifer.hardware.information.service.impl;

import com.github.huifer.hardware.information.dto.DeviceTypeRelationDTO;
import com.github.huifer.hardware.information.entity.DeviceTypeRelation;
import com.github.huifer.hardware.information.repository.DeviceTypeRelationRepository;
import com.github.huifer.hardware.information.service.DeviceTypeRelationService;
import com.github.huifer.hardware.information.vo.DeviceTypeRelationUpdateVO;
import com.github.huifer.hardware.information.vo.DeviceTypeRelationVO;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeviceTypeRelationServiceImpl implements DeviceTypeRelationService {

  @Autowired
  private DeviceTypeRelationRepository deviceTypeRelationRepository;

  @Transactional(rollbackFor = {Exception.class})
  public Long save(DeviceTypeRelationVO vO) {
    DeviceTypeRelation bean = new DeviceTypeRelation();
    bean.setDeviceId(vO.getDeviceId());
    bean.setTypeId(vO.getTypeId());
    bean.setCreateTime(LocalDateTime.now());
    bean.setDeleted(false);
    bean = deviceTypeRelationRepository.save(bean);
    return bean.getId();
  }

  @Transactional(rollbackFor = {Exception.class})
  public Boolean delete(Long id) {
    DeviceTypeRelation deviceTypeRelation = requireOne(id);
    deviceTypeRelation.setDeleted(true);
    deviceTypeRelation.setUpdateTime(LocalDateTime.now());
     return deviceTypeRelationRepository.save(deviceTypeRelation) != null;
  }



  @Transactional(rollbackFor = {Exception.class})
  public Boolean update(Long id, DeviceTypeRelationUpdateVO vO) {
    DeviceTypeRelation bean = requireOne(id);
    bean.setDeviceId(vO.getDeviceId());
    bean.setTypeId(vO.getTypeId());
    bean.setUpdateTime(LocalDateTime.now());
    return deviceTypeRelationRepository.save(bean) != null;
  }

  public DeviceTypeRelationDTO getById(Long id) {
    Optional<DeviceTypeRelation> byId = deviceTypeRelationRepository.findById(id);
    DeviceTypeRelation original = requireOne(id);
    if (byId.isPresent()){
      original =  byId.get();
    }
    return toDTO(original);
  }


  private DeviceTypeRelationDTO toDTO(DeviceTypeRelation original) {
    DeviceTypeRelationDTO bean = new DeviceTypeRelationDTO();
    BeanUtils.copyProperties(original, bean);
    return bean;
  }

  private DeviceTypeRelation requireOne(Long id) {
    return deviceTypeRelationRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("此数据不存在请刷新: " + id));
  }
}
