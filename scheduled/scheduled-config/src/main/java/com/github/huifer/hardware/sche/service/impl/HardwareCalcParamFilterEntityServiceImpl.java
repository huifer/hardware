package com.github.huifer.hardware.sche.service.impl;


import com.github.huifer.hardware.sche.dto.HardwareCalcParamFilterEntityDTO;
import com.github.huifer.hardware.sche.entity.HardwareCalcParamFilterEntity;
import com.github.huifer.hardware.sche.repository.HardwareCalcParamFilterEntityRepository;
import com.github.huifer.hardware.sche.service.HardwareCalcParamFilterEntityService;
import com.github.huifer.hardware.sche.vo.HardwareCalcParamFilterEntityQueryVO;
import com.github.huifer.hardware.sche.vo.HardwareCalcParamFilterEntityUpdateVO;
import com.github.huifer.hardware.sche.vo.HardwareCalcParamFilterEntityVO;
import java.util.NoSuchElementException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class HardwareCalcParamFilterEntityServiceImpl implements
    HardwareCalcParamFilterEntityService {

  @Autowired
  private HardwareCalcParamFilterEntityRepository hardwareCalcParamFilterEntityRepository;

  public Long save(HardwareCalcParamFilterEntityVO vO) {
    HardwareCalcParamFilterEntity bean = new HardwareCalcParamFilterEntity();
    BeanUtils.copyProperties(vO, bean);
    bean = hardwareCalcParamFilterEntityRepository.save(bean);
    return bean.getId();
  }

  public void delete(Long id) {
    hardwareCalcParamFilterEntityRepository.deleteById(id);
  }

  public void update(Long id, HardwareCalcParamFilterEntityUpdateVO vO) {
    HardwareCalcParamFilterEntity bean = requireOne(id);
    BeanUtils.copyProperties(vO, bean);
    hardwareCalcParamFilterEntityRepository.save(bean);
  }

  public HardwareCalcParamFilterEntityDTO getById(Long id) {
    HardwareCalcParamFilterEntity original = requireOne(id);
    return toDTO(original);
  }

  public Page<HardwareCalcParamFilterEntityDTO> query(HardwareCalcParamFilterEntityQueryVO vO) {
    throw new UnsupportedOperationException();
  }

  private HardwareCalcParamFilterEntityDTO toDTO(HardwareCalcParamFilterEntity original) {
    HardwareCalcParamFilterEntityDTO bean = new HardwareCalcParamFilterEntityDTO();
    BeanUtils.copyProperties(original, bean);
    return bean;
  }

  private HardwareCalcParamFilterEntity requireOne(Long id) {
    return hardwareCalcParamFilterEntityRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
  }
}
