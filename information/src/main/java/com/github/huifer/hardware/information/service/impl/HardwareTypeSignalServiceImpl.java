package com.github.huifer.hardware.information.service.impl;

import com.github.huifer.hardware.information.dto.HardwareTypeSignalDTO;
import com.github.huifer.hardware.information.entity.HardwareTypeSignal;
import com.github.huifer.hardware.information.repository.HardwareTypeSignalRepository;
import com.github.huifer.hardware.information.service.HardwareTypeSignalService;
import com.github.huifer.hardware.information.vo.HardwareTypeSignalUpdateVO;
import com.github.huifer.hardware.information.vo.HardwareTypeSignalVO;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HardwareTypeSignalServiceImpl implements HardwareTypeSignalService {

  @Autowired
  private HardwareTypeSignalRepository hardwareTypeSignalRepository;

  @Transactional(rollbackFor = {Exception.class})
  public Long save(HardwareTypeSignalVO vO) {
    HardwareTypeSignal bean = new HardwareTypeSignal();
    bean.setTypeId(vO.getTypeId());
    bean.setSignalId(vO.getSignalId());
    bean.setUpdateTime(LocalDateTime.now());
    bean.setCreateTime(LocalDateTime.now());
    bean.setDeleted(false);
    bean = hardwareTypeSignalRepository.save(bean);
    return bean.getId();
  }

  @Transactional(rollbackFor = {Exception.class})
  public Boolean delete(Long id) {
    HardwareTypeSignal hardwareTypeSignal = requireOne(id);
    hardwareTypeSignal.setDeleted(true);
    hardwareTypeSignal.setUpdateTime(LocalDateTime.now());
    return hardwareTypeSignalRepository.save(hardwareTypeSignal) !=null;
  }

  @Transactional(rollbackFor = {Exception.class})
  public Boolean update(Long id, HardwareTypeSignalUpdateVO vO) {
    HardwareTypeSignal bean = requireOne(id);
    bean.setTypeId(vO.getTypeId());
    bean.setSignalId(vO.getSignalId());
    bean.setUpdateTime(LocalDateTime.now());
    return hardwareTypeSignalRepository.save(bean) != null;
  }

  public HardwareTypeSignalDTO getById(Long id) {
    Optional<HardwareTypeSignal> byId = hardwareTypeSignalRepository.findById(id);
    HardwareTypeSignal original = new HardwareTypeSignal();
    if (byId.isPresent()) {
      original = byId.get();
    }
    return toDTO(original);
  }



  private HardwareTypeSignalDTO toDTO(HardwareTypeSignal original) {
    HardwareTypeSignalDTO bean = new HardwareTypeSignalDTO();
    BeanUtils.copyProperties(original, bean);
    return bean;
  }

  private HardwareTypeSignal requireOne(Long id) {
    return hardwareTypeSignalRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("此数据不存在请刷新: " + id));
  }
}
