package com.github.huifer.hardware.sche.service.impl;


import com.github.huifer.hardware.sche.dto.HardwareCalcParamsEntityDTO;
import com.github.huifer.hardware.sche.entity.HardwareCalcParamsEntity;
import com.github.huifer.hardware.sche.repository.HardwareCalcParamsEntityRepository;
import com.github.huifer.hardware.sche.service.HardwareCalcParamsEntityService;
import com.github.huifer.hardware.sche.vo.HardwareCalcParamsEntityQueryVO;
import com.github.huifer.hardware.sche.vo.HardwareCalcParamsEntityUpdateVO;
import com.github.huifer.hardware.sche.vo.HardwareCalcParamsEntityVO;
import java.util.NoSuchElementException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class HardwareCalcParamsEntityServiceImpl implements HardwareCalcParamsEntityService {

  @Autowired
  private HardwareCalcParamsEntityRepository hardwareCalcParamsEntityRepository;

  public Long save(HardwareCalcParamsEntityVO vO) {
    HardwareCalcParamsEntity bean = new HardwareCalcParamsEntity();
    BeanUtils.copyProperties(vO, bean);
    bean = hardwareCalcParamsEntityRepository.save(bean);
    return bean.getId();
  }

  public void delete(Long id) {
    hardwareCalcParamsEntityRepository.deleteById(id);
  }

  public void update(Long id, HardwareCalcParamsEntityUpdateVO vO) {
    HardwareCalcParamsEntity bean = requireOne(id);
    BeanUtils.copyProperties(vO, bean);
    hardwareCalcParamsEntityRepository.save(bean);
  }

  public HardwareCalcParamsEntityDTO getById(Long id) {
    HardwareCalcParamsEntity original = requireOne(id);
    return toDTO(original);
  }

  public Page<HardwareCalcParamsEntityDTO> query(HardwareCalcParamsEntityQueryVO vO) {
    throw new UnsupportedOperationException();
  }

  private HardwareCalcParamsEntityDTO toDTO(HardwareCalcParamsEntity original) {
    HardwareCalcParamsEntityDTO bean = new HardwareCalcParamsEntityDTO();
    BeanUtils.copyProperties(original, bean);
    return bean;
  }

  private HardwareCalcParamsEntity requireOne(Long id) {
    return hardwareCalcParamsEntityRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
  }
}
