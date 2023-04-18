package com.github.huifer.hardware.information.vo.impl;

import com.github.huifer.hardware.information.entity.HardwareExtensionInfo;
import com.github.huifer.hardware.information.repository.HardwareExtensionInfoRepository;
import com.github.huifer.hardware.information.dto.HardwareExtensionInfoDTO;
import com.github.huifer.hardware.information.service.HardwareExtensionInfoService;
import com.github.huifer.hardware.information.vo.HardwareExtensionInfoQueryVO;
import com.github.huifer.hardware.information.vo.HardwareExtensionInfoUpdateVO;
import com.github.huifer.hardware.information.vo.HardwareExtensionInfoVO;
import com.github.huifer.hardware.information.vo.HardwareExtensionInfoVO;
import java.util.NoSuchElementException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class HardwareExtensionInfoServiceImpl implements HardwareExtensionInfoService {

  @Autowired
  private HardwareExtensionInfoRepository hardwareExtensionInfoRepository;

  public Long save(HardwareExtensionInfoVO vO) {
    HardwareExtensionInfo bean = new HardwareExtensionInfo();
    BeanUtils.copyProperties(vO, bean);
    bean = hardwareExtensionInfoRepository.save(bean);
    return bean.getId();
  }

  public void delete(Long id) {
    hardwareExtensionInfoRepository.deleteById(id);
  }

  public void update(Long id, HardwareExtensionInfoUpdateVO vO) {
    HardwareExtensionInfo bean = requireOne(id);
    BeanUtils.copyProperties(vO, bean);
    hardwareExtensionInfoRepository.save(bean);
  }

  public HardwareExtensionInfoDTO getById(Long id) {
    HardwareExtensionInfo original = requireOne(id);
    return toDTO(original);
  }

  public Page<HardwareExtensionInfoDTO> query(HardwareExtensionInfoQueryVO vO) {
    throw new UnsupportedOperationException();
  }

  private HardwareExtensionInfoDTO toDTO(HardwareExtensionInfo original) {
    HardwareExtensionInfoDTO bean = new HardwareExtensionInfoDTO();
    BeanUtils.copyProperties(original, bean);
    return bean;
  }

  private HardwareExtensionInfo requireOne(Long id) {
    return hardwareExtensionInfoRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
  }
}
