package com.github.huifer.hardware.information.service.impl;

import com.github.huifer.hardware.information.dto.HardwareTypeSignalDTO;
import com.github.huifer.hardware.information.entity.HardwareTypeSignalEntity;
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
    HardwareTypeSignalEntity bean = new HardwareTypeSignalEntity();
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
    HardwareTypeSignalEntity hardwareTypeSignal = requireOne(id);
    hardwareTypeSignal.setDeleted(true);
    hardwareTypeSignal.setUpdateTime(LocalDateTime.now());
    return hardwareTypeSignalRepository.save(hardwareTypeSignal) !=null;
  }

  @Transactional(rollbackFor = {Exception.class})
  public Boolean update(Long id, HardwareTypeSignalUpdateVO vO) {
    HardwareTypeSignalEntity bean = requireOne(id);
    bean.setTypeId(vO.getTypeId());
    bean.setSignalId(vO.getSignalId());
    bean.setUpdateTime(LocalDateTime.now());
    return hardwareTypeSignalRepository.save(bean) != null;
  }

  public HardwareTypeSignalDTO getById(Long id) {
    Optional<HardwareTypeSignalEntity> byId = hardwareTypeSignalRepository.findById(id);
    HardwareTypeSignalEntity original = new HardwareTypeSignalEntity();
    if (byId.isPresent()) {
      original = byId.get();
    }
    return toDTO(original);
  }



  private HardwareTypeSignalDTO toDTO(HardwareTypeSignalEntity original) {
    HardwareTypeSignalDTO bean = new HardwareTypeSignalDTO();
    BeanUtils.copyProperties(original, bean);
    return bean;
  }

  private HardwareTypeSignalEntity requireOne(Long id) {
    return hardwareTypeSignalRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("此数据不存在请刷新: " + id));
  }
}
