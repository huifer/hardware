package com.github.huifer.hardware.sche.service.impl;


import com.github.huifer.hardware.sche.dto.HardwareStaticCalcParamEntityDTO;
import com.github.huifer.hardware.sche.entity.HardwareStaticCalcParamEntity;
import com.github.huifer.hardware.sche.repository.HardwareStaticCalcParamEntityRepository;
import com.github.huifer.hardware.sche.service.HardwareStaticCalcParamEntityService;
import com.github.huifer.hardware.sche.vo.HardwareStaticCalcParamEntityQueryVO;
import com.github.huifer.hardware.sche.vo.HardwareStaticCalcParamEntityUpdateVO;
import com.github.huifer.hardware.sche.vo.HardwareStaticCalcParamEntityVO;
import java.util.NoSuchElementException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class HardwareStaticCalcParamEntityServiceImpl implements
    HardwareStaticCalcParamEntityService {

  @Autowired
  private HardwareStaticCalcParamEntityRepository hardwareStaticCalcParamEntityRepository;

  public Long save(HardwareStaticCalcParamEntityVO vO) {
    HardwareStaticCalcParamEntity bean = new HardwareStaticCalcParamEntity();
    BeanUtils.copyProperties(vO, bean);
    bean = hardwareStaticCalcParamEntityRepository.save(bean);
    return bean.getId();
  }

  public void delete(Long id) {
    hardwareStaticCalcParamEntityRepository.deleteById(id);
  }

  public void update(Long id, HardwareStaticCalcParamEntityUpdateVO vO) {
    HardwareStaticCalcParamEntity bean = requireOne(id);
    BeanUtils.copyProperties(vO, bean);
    hardwareStaticCalcParamEntityRepository.save(bean);
  }

  public HardwareStaticCalcParamEntityDTO getById(Long id) {
    HardwareStaticCalcParamEntity original = requireOne(id);
    return toDTO(original);
  }

  public Page<HardwareStaticCalcParamEntityDTO> query(HardwareStaticCalcParamEntityQueryVO vO) {
    throw new UnsupportedOperationException();
  }

  private HardwareStaticCalcParamEntityDTO toDTO(HardwareStaticCalcParamEntity original) {
    HardwareStaticCalcParamEntityDTO bean = new HardwareStaticCalcParamEntityDTO();
    BeanUtils.copyProperties(original, bean);
    return bean;
  }

  private HardwareStaticCalcParamEntity requireOne(Long id) {
    return hardwareStaticCalcParamEntityRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
  }
}
