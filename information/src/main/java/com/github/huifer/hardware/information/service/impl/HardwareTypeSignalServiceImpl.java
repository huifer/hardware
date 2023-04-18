package com.github.huifer.hardware.information.service.impl;

import com.github.huifer.hardware.information.dto.HardwareTypeSignalDTO;
import com.github.huifer.hardware.information.entity.HardwareTypeSignal;
import com.github.huifer.hardware.information.repository.HardwareTypeSignalRepository;
import com.github.huifer.hardware.information.service.HardwareTypeSignalService;
import com.github.huifer.hardware.information.vo.HardwareTypeSignalQueryVO;
import com.github.huifer.hardware.information.vo.HardwareTypeSignalUpdateVO;
import com.github.huifer.hardware.information.vo.HardwareTypeSignalVO;
import java.util.NoSuchElementException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class HardwareTypeSignalServiceImpl implements HardwareTypeSignalService {

  @Autowired
  private HardwareTypeSignalRepository hardwareTypeSignalRepository;

  public Long save(HardwareTypeSignalVO vO) {
    HardwareTypeSignal bean = new HardwareTypeSignal();
    BeanUtils.copyProperties(vO, bean);
    bean = hardwareTypeSignalRepository.save(bean);
    return bean.getId();
  }

  public void delete(Long id) {
    hardwareTypeSignalRepository.deleteById(id);
  }

  public void update(Long id, HardwareTypeSignalUpdateVO vO) {
    HardwareTypeSignal bean = requireOne(id);
    BeanUtils.copyProperties(vO, bean);
    hardwareTypeSignalRepository.save(bean);
  }

  public HardwareTypeSignalDTO getById(Long id) {
    HardwareTypeSignal original = requireOne(id);
    return toDTO(original);
  }

  public Page<HardwareTypeSignalDTO> query(HardwareTypeSignalQueryVO vO) {
    throw new UnsupportedOperationException();
  }

  private HardwareTypeSignalDTO toDTO(HardwareTypeSignal original) {
    HardwareTypeSignalDTO bean = new HardwareTypeSignalDTO();
    BeanUtils.copyProperties(original, bean);
    return bean;
  }

  private HardwareTypeSignal requireOne(Long id) {
    return hardwareTypeSignalRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
  }
}
