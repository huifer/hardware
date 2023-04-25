package com.github.huifer.hardware.sche.service.impl;


import com.github.huifer.hardware.sche.dto.HardwareCalcParamMappingQueryEntityDTO;
import com.github.huifer.hardware.sche.entity.HardwareCalcParamMappingQueryEntity;
import com.github.huifer.hardware.sche.repository.HardwareCalcParamMappingQueryEntityRepository;
import com.github.huifer.hardware.sche.service.HardwareCalcParamMappingQueryEntityService;
import com.github.huifer.hardware.sche.vo.HardwareCalcParamMappingQueryEntityQueryVO;
import com.github.huifer.hardware.sche.vo.HardwareCalcParamMappingQueryEntityUpdateVO;
import com.github.huifer.hardware.sche.vo.HardwareCalcParamMappingQueryEntityVO;
import java.util.NoSuchElementException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class HardwareCalcParamMappingQueryEntityServiceImpl implements
    HardwareCalcParamMappingQueryEntityService {

  @Autowired
  private HardwareCalcParamMappingQueryEntityRepository hardwareCalcParamMappingQueryEntityRepository;

  public Long save(HardwareCalcParamMappingQueryEntityVO vO) {
    HardwareCalcParamMappingQueryEntity bean = new HardwareCalcParamMappingQueryEntity();
    BeanUtils.copyProperties(vO, bean);
    bean = hardwareCalcParamMappingQueryEntityRepository.save(bean);
    return bean.getId();
  }

  public void delete(Long id) {
    hardwareCalcParamMappingQueryEntityRepository.deleteById(id);
  }

  public void update(Long id, HardwareCalcParamMappingQueryEntityUpdateVO vO) {
    HardwareCalcParamMappingQueryEntity bean = requireOne(id);
    BeanUtils.copyProperties(vO, bean);
    hardwareCalcParamMappingQueryEntityRepository.save(bean);
  }

  public HardwareCalcParamMappingQueryEntityDTO getById(Long id) {
    HardwareCalcParamMappingQueryEntity original = requireOne(id);
    return toDTO(original);
  }

  public Page<HardwareCalcParamMappingQueryEntityDTO> query(
      HardwareCalcParamMappingQueryEntityQueryVO vO) {
    throw new UnsupportedOperationException();
  }

  private HardwareCalcParamMappingQueryEntityDTO toDTO(
      HardwareCalcParamMappingQueryEntity original) {
    HardwareCalcParamMappingQueryEntityDTO bean = new HardwareCalcParamMappingQueryEntityDTO();
    BeanUtils.copyProperties(original, bean);
    return bean;
  }

  private HardwareCalcParamMappingQueryEntity requireOne(Long id) {
    return hardwareCalcParamMappingQueryEntityRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
  }
}
