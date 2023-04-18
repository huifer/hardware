package com.github.huifer.hardware.information.service.impl;

import com.github.huifer.hardware.information.entity.HardwareType;
import com.github.huifer.hardware.information.repository.HardwareTypeRepository;
import com.github.huifer.hardware.information.service.HardwareTypeService;

import com.github.huifer.hardware.information.vo.HardwareTypeDTO;
import com.github.huifer.hardware.information.vo.HardwareTypeQueryVO;
import com.github.huifer.hardware.information.vo.HardwareTypeUpdateVO;
import com.github.huifer.hardware.information.vo.HardwareTypeVO;
import java.util.NoSuchElementException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class HardwareTypeServiceImpl implements HardwareTypeService {

  @Autowired
  private HardwareTypeRepository hardwareTypeRepository;

  public Long save(HardwareTypeVO vO) {
    HardwareType bean = new HardwareType();
    BeanUtils.copyProperties(vO, bean);
    bean = hardwareTypeRepository.save(bean);
    return bean.getId();
  }

  public void delete(Long id) {
    hardwareTypeRepository.deleteById(id);
  }

  public void update(Long id, HardwareTypeUpdateVO vO) {
    HardwareType bean = requireOne(id);
    BeanUtils.copyProperties(vO, bean);
    hardwareTypeRepository.save(bean);
  }

  public HardwareTypeDTO getById(Long id) {
    HardwareType original = requireOne(id);
    return toDTO(original);
  }

  public Page<HardwareTypeDTO> query(HardwareTypeQueryVO vO) {
    throw new UnsupportedOperationException();
  }

  private HardwareTypeDTO toDTO(HardwareType original) {
    HardwareTypeDTO bean = new HardwareTypeDTO();
    BeanUtils.copyProperties(original, bean);
    return bean;
  }

  private HardwareType requireOne(Long id) {
    return hardwareTypeRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
  }
}
