package com.github.huifer.hardware.information.service.impl;

import com.github.huifer.hardware.information.dto.HardwareSignalDTO;
import com.github.huifer.hardware.information.entity.HardwareSignal;
import com.github.huifer.hardware.information.repository.HardwareSignalRepository;
import com.github.huifer.hardware.information.service.HardwareSignalService;
import com.github.huifer.hardware.information.vo.HardwareSignalQueryVO;
import com.github.huifer.hardware.information.vo.HardwareSignalUpdateVO;
import com.github.huifer.hardware.information.vo.HardwareSignalVO;
import java.util.NoSuchElementException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class HardwareSignalServiceImpl implements HardwareSignalService {

  @Autowired
  private HardwareSignalRepository hardwareSignalRepository;

  public Long save(HardwareSignalVO vO) {
    HardwareSignal bean = new HardwareSignal();
    BeanUtils.copyProperties(vO, bean);
    bean = hardwareSignalRepository.save(bean);
    return bean.getId();
  }

  public void delete(Long id) {
    hardwareSignalRepository.deleteById(id);
  }

  public void update(Long id, HardwareSignalUpdateVO vO) {
    HardwareSignal bean = requireOne(id);
    BeanUtils.copyProperties(vO, bean);
    hardwareSignalRepository.save(bean);
  }

  public HardwareSignalDTO getById(Long id) {
    HardwareSignal original = requireOne(id);
    return toDTO(original);
  }

  public Page<HardwareSignalDTO> query(HardwareSignalQueryVO vO) {
    throw new UnsupportedOperationException();
  }

  private HardwareSignalDTO toDTO(HardwareSignal original) {
    HardwareSignalDTO bean = new HardwareSignalDTO();
    BeanUtils.copyProperties(original, bean);
    return bean;
  }

  private HardwareSignal requireOne(Long id) {
    return hardwareSignalRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
  }
}
