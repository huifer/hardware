package com.github.huifer.hardware.information.service.impl;

import com.github.huifer.hardware.information.dto.HardwareTypeSignalIdentifierDTO;
import com.github.huifer.hardware.information.entity.HardwareTypeSignalIdentifier;
import com.github.huifer.hardware.information.repository.HardwareTypeSignalIdentifierRepository;
import com.github.huifer.hardware.information.service.HardwareTypeSignalIdentifierService;

import com.github.huifer.hardware.information.vo.HardwareTypeSignalIdentifierQueryVO;
import com.github.huifer.hardware.information.vo.HardwareTypeSignalIdentifierUpdateVO;
import com.github.huifer.hardware.information.vo.HardwareTypeSignalIdentifierVO;
import java.util.NoSuchElementException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class HardwareTypeSignalIdentifierServiceImpl implements
    HardwareTypeSignalIdentifierService {

  @Autowired
  private HardwareTypeSignalIdentifierRepository hardwareTypeSignalIdentifierRepository;

  public Long save(HardwareTypeSignalIdentifierVO vO) {
    HardwareTypeSignalIdentifier bean = new HardwareTypeSignalIdentifier();
    BeanUtils.copyProperties(vO, bean);
    bean = hardwareTypeSignalIdentifierRepository.save(bean);
    return bean.getId();
  }

  public void delete(Long id) {
    hardwareTypeSignalIdentifierRepository.deleteById(id);
  }

  public void update(Long id, HardwareTypeSignalIdentifierUpdateVO vO) {
    HardwareTypeSignalIdentifier bean = requireOne(id);
    BeanUtils.copyProperties(vO, bean);
    hardwareTypeSignalIdentifierRepository.save(bean);
  }

  public HardwareTypeSignalIdentifierDTO getById(Long id) {
    HardwareTypeSignalIdentifier original = requireOne(id);
    return toDTO(original);
  }

  public Page<HardwareTypeSignalIdentifierDTO> query(HardwareTypeSignalIdentifierQueryVO vO) {
    throw new UnsupportedOperationException();
  }

  private HardwareTypeSignalIdentifierDTO toDTO(HardwareTypeSignalIdentifier original) {
    HardwareTypeSignalIdentifierDTO bean = new HardwareTypeSignalIdentifierDTO();
    BeanUtils.copyProperties(original, bean);
    return bean;
  }

  private HardwareTypeSignalIdentifier requireOne(Long id) {
    return hardwareTypeSignalIdentifierRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
  }
}
