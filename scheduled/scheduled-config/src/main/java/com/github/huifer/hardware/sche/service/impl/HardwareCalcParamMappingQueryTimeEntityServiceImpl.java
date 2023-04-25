package com.github.huifer.hardware.sche.service.impl;


import com.github.huifer.hardware.sche.dto.HardwareCalcParamMappingQueryTimeEntityDTO;
import com.github.huifer.hardware.sche.entity.HardwareCalcParamMappingQueryTimeEntity;
import com.github.huifer.hardware.sche.repository.HardwareCalcParamMappingQueryTimeEntityRepository;
import com.github.huifer.hardware.sche.service.HardwareCalcParamMappingQueryTimeEntityService;
import com.github.huifer.hardware.sche.vo.HardwareCalcParamMappingQueryTimeEntityQueryVO;
import com.github.huifer.hardware.sche.vo.HardwareCalcParamMappingQueryTimeEntityUpdateVO;
import com.github.huifer.hardware.sche.vo.HardwareCalcParamMappingQueryTimeEntityVO;
import java.util.NoSuchElementException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class HardwareCalcParamMappingQueryTimeEntityServiceImpl implements
    HardwareCalcParamMappingQueryTimeEntityService {

  @Autowired
  private HardwareCalcParamMappingQueryTimeEntityRepository hardwareCalcParamMappingQueryTimeEntityRepository;

  public Long save(HardwareCalcParamMappingQueryTimeEntityVO vO) {
    HardwareCalcParamMappingQueryTimeEntity bean = new HardwareCalcParamMappingQueryTimeEntity();
    BeanUtils.copyProperties(vO, bean);
    bean = hardwareCalcParamMappingQueryTimeEntityRepository.save(bean);
    return bean.getId();
  }

  public void delete(Long id) {
    hardwareCalcParamMappingQueryTimeEntityRepository.deleteById(id);
  }

  public void update(Long id, HardwareCalcParamMappingQueryTimeEntityUpdateVO vO) {
    HardwareCalcParamMappingQueryTimeEntity bean = requireOne(id);
    BeanUtils.copyProperties(vO, bean);
    hardwareCalcParamMappingQueryTimeEntityRepository.save(bean);
  }

  public HardwareCalcParamMappingQueryTimeEntityDTO getById(Long id) {
    HardwareCalcParamMappingQueryTimeEntity original = requireOne(id);
    return toDTO(original);
  }

  public Page<HardwareCalcParamMappingQueryTimeEntityDTO> query(
      HardwareCalcParamMappingQueryTimeEntityQueryVO vO) {
    throw new UnsupportedOperationException();
  }

  private HardwareCalcParamMappingQueryTimeEntityDTO toDTO(
      HardwareCalcParamMappingQueryTimeEntity original) {
    HardwareCalcParamMappingQueryTimeEntityDTO bean = new HardwareCalcParamMappingQueryTimeEntityDTO();
    BeanUtils.copyProperties(original, bean);
    return bean;
  }

  private HardwareCalcParamMappingQueryTimeEntity requireOne(Long id) {
    return hardwareCalcParamMappingQueryTimeEntityRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
  }
}
