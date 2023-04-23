package com.github.huifer.hardware.information.service.impl;
import java.time.LocalDateTime;

import com.github.huifer.hardware.information.dto.HardwareTypeSignalIdentifierDTO;
import com.github.huifer.hardware.information.entity.HardwareTypeSignalIdentifierEntity;
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
import org.springframework.transaction.annotation.Transactional;

@Service
public class HardwareTypeSignalIdentifierServiceImpl implements
    HardwareTypeSignalIdentifierService {

  @Autowired
  private HardwareTypeSignalIdentifierRepository hardwareTypeSignalIdentifierRepository;

  @Transactional(rollbackFor = {Exception.class})
  public Long save(HardwareTypeSignalIdentifierVO vO) {
    HardwareTypeSignalIdentifierEntity bean = new HardwareTypeSignalIdentifierEntity();
    bean.setTypeId(vO.getTypeId());
    bean.setSignalId(vO.getSignalId());
    bean.setUpdateTime(LocalDateTime.now());
    bean.setCreateTime(LocalDateTime.now());
    bean.setDeleted(false);
    bean = hardwareTypeSignalIdentifierRepository.save(bean);
    return bean.getId();
  }

  @Transactional(rollbackFor = {Exception.class})
  public Boolean delete(Long id) {
    HardwareTypeSignalIdentifierEntity hardwareTypeSignalIdentifier = requireOne(id);
    hardwareTypeSignalIdentifier.setDeleted(true);
    hardwareTypeSignalIdentifier.setUpdateTime(LocalDateTime.now());
    return hardwareTypeSignalIdentifierRepository.save(hardwareTypeSignalIdentifier) != null;
  }

  @Transactional(rollbackFor = {Exception.class})
  public Boolean update(Long id, HardwareTypeSignalIdentifierUpdateVO vO) {
    HardwareTypeSignalIdentifierEntity bean = requireOne(id);
    bean.setTypeId(vO.getTypeId());
    bean.setSignalId(vO.getSignalId());
    bean.setUpdateTime(LocalDateTime.now());
    return hardwareTypeSignalIdentifierRepository.save(bean) != null;
  }

  public HardwareTypeSignalIdentifierDTO getById(Long id) {
    HardwareTypeSignalIdentifierEntity original = requireOne(id);
    return toDTO(original);
  }

  public Page<HardwareTypeSignalIdentifierDTO> query(HardwareTypeSignalIdentifierQueryVO vO) {
    throw new UnsupportedOperationException();
  }

  private HardwareTypeSignalIdentifierDTO toDTO(HardwareTypeSignalIdentifierEntity original) {
    HardwareTypeSignalIdentifierDTO bean = new HardwareTypeSignalIdentifierDTO();
    BeanUtils.copyProperties(original, bean);
    return bean;
  }

  private HardwareTypeSignalIdentifierEntity requireOne(Long id) {
    return hardwareTypeSignalIdentifierRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("此数据不存在请刷新: " + id));
  }
}
