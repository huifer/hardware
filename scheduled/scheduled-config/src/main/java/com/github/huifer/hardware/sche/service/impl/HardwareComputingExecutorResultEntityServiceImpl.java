package com.github.huifer.hardware.sche.service.impl;


import com.github.huifer.hardware.sche.dto.HardwareComputingExecutorResultEntityDTO;
import com.github.huifer.hardware.sche.entity.HardwareComputingExecutorResultEntity;
import com.github.huifer.hardware.sche.repository.HardwareComputingExecutorResultEntityRepository;
import com.github.huifer.hardware.sche.service.HardwareComputingExecutorResultEntityService;
import com.github.huifer.hardware.sche.vo.HardwareComputingExecutorResultEntityQueryVO;
import com.github.huifer.hardware.sche.vo.HardwareComputingExecutorResultEntityUpdateVO;
import com.github.huifer.hardware.sche.vo.HardwareComputingExecutorResultEntityVO;
import java.util.NoSuchElementException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class HardwareComputingExecutorResultEntityServiceImpl implements
    HardwareComputingExecutorResultEntityService {

  @Autowired
  private HardwareComputingExecutorResultEntityRepository hardwareComputingExecutorResultEntityRepository;

  public Long save(HardwareComputingExecutorResultEntityVO vO) {
    HardwareComputingExecutorResultEntity bean = new HardwareComputingExecutorResultEntity();
    BeanUtils.copyProperties(vO, bean);
    bean = hardwareComputingExecutorResultEntityRepository.save(bean);
    return bean.getId();
  }

  public void delete(Long id) {
    hardwareComputingExecutorResultEntityRepository.deleteById(id);
  }

  public void update(Long id, HardwareComputingExecutorResultEntityUpdateVO vO) {
    HardwareComputingExecutorResultEntity bean = requireOne(id);
    BeanUtils.copyProperties(vO, bean);
    hardwareComputingExecutorResultEntityRepository.save(bean);
  }

  public HardwareComputingExecutorResultEntityDTO getById(Long id) {
    HardwareComputingExecutorResultEntity original = requireOne(id);
    return toDTO(original);
  }

  public Page<HardwareComputingExecutorResultEntityDTO> query(
      HardwareComputingExecutorResultEntityQueryVO vO) {
    throw new UnsupportedOperationException();
  }

  private HardwareComputingExecutorResultEntityDTO toDTO(
      HardwareComputingExecutorResultEntity original) {
    HardwareComputingExecutorResultEntityDTO bean = new HardwareComputingExecutorResultEntityDTO();
    BeanUtils.copyProperties(original, bean);
    return bean;
  }

  private HardwareComputingExecutorResultEntity requireOne(Long id) {
    return hardwareComputingExecutorResultEntityRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
  }
}
