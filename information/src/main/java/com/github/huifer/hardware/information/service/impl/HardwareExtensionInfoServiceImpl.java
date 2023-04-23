package com.github.huifer.hardware.information.service.impl;

import com.github.huifer.hardware.information.dto.HardwareExtensionInfoDTO;
import com.github.huifer.hardware.information.entity.HardwareExtensionInfoEntity;
import com.github.huifer.hardware.information.repository.HardwareExtensionInfoRepository;
import com.github.huifer.hardware.information.service.HardwareExtensionInfoService;
import com.github.huifer.hardware.information.vo.HardwareExtensionInfoUpdateVO;
import com.github.huifer.hardware.information.vo.HardwareExtensionInfoVO;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HardwareExtensionInfoServiceImpl implements HardwareExtensionInfoService {

  @Autowired
  private HardwareExtensionInfoRepository hardwareExtensionInfoRepository;

  @Transactional(rollbackFor = {Exception.class})

  public Long save(HardwareExtensionInfoVO vO) {
    HardwareExtensionInfoEntity bean = new HardwareExtensionInfoEntity();
    bean.setDeviceId(vO.getDeviceId());
    bean.setExtInfo(vO.getExtInfo());
    bean.setUpdateTime(LocalDateTime.now());
    bean.setCreateTime(LocalDateTime.now());
    bean.setDeleted(false);
    bean = hardwareExtensionInfoRepository.save(bean);
    return bean.getId();
  }

  @Transactional(rollbackFor = {Exception.class})
  public Boolean delete(Long id) {
    HardwareExtensionInfoEntity hardwareExtensionInfo = requireOne(id);
    hardwareExtensionInfo.setDeleted(true);
    hardwareExtensionInfo.setUpdateTime(LocalDateTime.now());
    return hardwareExtensionInfoRepository.save(hardwareExtensionInfo) != null;
  }


  @Transactional(rollbackFor = {Exception.class})
  public Boolean update(Long id, HardwareExtensionInfoUpdateVO vO) {
    HardwareExtensionInfoEntity bean = requireOne(id);
    bean.setExtInfo(vO.getExtInfo());
    bean.setUpdateTime(LocalDateTime.now());
    return hardwareExtensionInfoRepository.save(bean) != null;
  }

  public HardwareExtensionInfoDTO getById(Long id) {
    Optional<HardwareExtensionInfoEntity> byId = hardwareExtensionInfoRepository.findById(id);
    HardwareExtensionInfoEntity hardwareExtensionInfo = new HardwareExtensionInfoEntity();
    if (byId.isPresent()) {
      hardwareExtensionInfo = byId.get();
    }
    return toDTO(hardwareExtensionInfo);
  }


  private HardwareExtensionInfoDTO toDTO(HardwareExtensionInfoEntity original) {
    HardwareExtensionInfoDTO bean = new HardwareExtensionInfoDTO();
    BeanUtils.copyProperties(original, bean);
    return bean;
  }

  private HardwareExtensionInfoEntity requireOne(Long id) {
    return hardwareExtensionInfoRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("此数据不存在请刷新: " + id));
  }
}
