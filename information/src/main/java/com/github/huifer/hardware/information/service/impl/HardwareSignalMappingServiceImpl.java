package com.github.huifer.hardware.information.service.impl;

import com.github.huifer.hardware.information.dto.HardwareSignalMappingDTO;
import com.github.huifer.hardware.information.entity.HardwareSignalMapping;
import com.github.huifer.hardware.information.repository.HardwareSignalMappingRepository;
import com.github.huifer.hardware.information.service.HardwareSignalMappingService;
import com.github.huifer.hardware.information.vo.HardwareSignalMappingQueryVO;
import com.github.huifer.hardware.information.vo.HardwareSignalMappingUpdateVO;
import com.github.huifer.hardware.information.vo.HardwareSignalMappingVO;
import java.util.NoSuchElementException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class HardwareSignalMappingServiceImpl implements HardwareSignalMappingService {

  @Autowired
  private HardwareSignalMappingRepository hardwareSignalMappingRepository;

  public Long save(HardwareSignalMappingVO vO) {
    HardwareSignalMapping bean = new HardwareSignalMapping();
    BeanUtils.copyProperties(vO, bean);
    bean = hardwareSignalMappingRepository.save(bean);
    return bean.getId();
  }

  public void delete(Long id) {
    hardwareSignalMappingRepository.deleteById(id);
  }

  public void update(Long id, HardwareSignalMappingUpdateVO vO) {
    HardwareSignalMapping bean = requireOne(id);
    BeanUtils.copyProperties(vO, bean);
    hardwareSignalMappingRepository.save(bean);
  }

  public HardwareSignalMappingDTO getById(Long id) {
    HardwareSignalMapping original = requireOne(id);
    return toDTO(original);
  }

  public Page<HardwareSignalMappingDTO> query(HardwareSignalMappingQueryVO vO) {
    throw new UnsupportedOperationException();
  }

  private HardwareSignalMappingDTO toDTO(HardwareSignalMapping original) {
    HardwareSignalMappingDTO bean = new HardwareSignalMappingDTO();
    BeanUtils.copyProperties(original, bean);
    return bean;
  }

  private HardwareSignalMapping requireOne(Long id) {
    return hardwareSignalMappingRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
  }
}
