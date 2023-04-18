package com.github.huifer.hardware.information.service.impl;

import com.github.huifer.hardware.information.dto.DeviceTypeRelationDTO;
import com.github.huifer.hardware.information.entity.DeviceTypeRelation;
import com.github.huifer.hardware.information.repository.DeviceTypeRelationRepository;
import com.github.huifer.hardware.information.service.DeviceTypeRelationService;
import com.github.huifer.hardware.information.vo.DeviceTypeRelationQueryVO;
import com.github.huifer.hardware.information.vo.DeviceTypeRelationUpdateVO;
import com.github.huifer.hardware.information.vo.DeviceTypeRelationVO;
import java.util.NoSuchElementException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class DeviceTypeRelationServiceImpl implements DeviceTypeRelationService {

  @Autowired
  private DeviceTypeRelationRepository deviceTypeRelationRepository;

  public Long save(DeviceTypeRelationVO vO) {
    DeviceTypeRelation bean = new DeviceTypeRelation();
    BeanUtils.copyProperties(vO, bean);
    bean = deviceTypeRelationRepository.save(bean);
    return bean.getId();
  }

  public void delete(Long id) {
    deviceTypeRelationRepository.deleteById(id);
  }

  public void update(Long id, DeviceTypeRelationUpdateVO vO) {
    DeviceTypeRelation bean = requireOne(id);
    BeanUtils.copyProperties(vO, bean);
    deviceTypeRelationRepository.save(bean);
  }

  public DeviceTypeRelationDTO getById(Long id) {
    DeviceTypeRelation original = requireOne(id);
    return toDTO(original);
  }

  public Page<DeviceTypeRelationDTO> query(DeviceTypeRelationQueryVO vO) {
    throw new UnsupportedOperationException();
  }

  private DeviceTypeRelationDTO toDTO(DeviceTypeRelation original) {
    DeviceTypeRelationDTO bean = new DeviceTypeRelationDTO();
    BeanUtils.copyProperties(original, bean);
    return bean;
  }

  private DeviceTypeRelation requireOne(Long id) {
    return deviceTypeRelationRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
  }
}
