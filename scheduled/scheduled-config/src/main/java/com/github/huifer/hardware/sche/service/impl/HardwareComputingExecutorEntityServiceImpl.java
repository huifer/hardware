package com.github.huifer.hardware.sche.service.impl;


import com.github.huifer.hardware.sche.dto.HardwareComputingExecutorEntityDTO;
import com.github.huifer.hardware.sche.entity.HardwareComputingExecutorEntity;
import com.github.huifer.hardware.sche.repository.HardwareComputingExecutorEntityRepository;
import com.github.huifer.hardware.sche.service.HardwareComputingExecutorEntityService;
import com.github.huifer.hardware.sche.vo.HardwareComputingExecutorEntityQueryVO;
import com.github.huifer.hardware.sche.vo.HardwareComputingExecutorEntityUpdateVO;
import com.github.huifer.hardware.sche.vo.HardwareComputingExecutorEntityVO;
import java.util.NoSuchElementException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class HardwareComputingExecutorEntityServiceImpl implements
    HardwareComputingExecutorEntityService {

  @Autowired
  private HardwareComputingExecutorEntityRepository hardwareComputingExecutorEntityRepository;

  public Long save(HardwareComputingExecutorEntityVO vO) {
    HardwareComputingExecutorEntity bean = new HardwareComputingExecutorEntity();
    BeanUtils.copyProperties(vO, bean);
    bean = hardwareComputingExecutorEntityRepository.save(bean);
    return bean.getId();
  }

  public void delete(Long id) {
    hardwareComputingExecutorEntityRepository.deleteById(id);
  }

  public void update(Long id, HardwareComputingExecutorEntityUpdateVO vO) {
    HardwareComputingExecutorEntity bean = requireOne(id);
    BeanUtils.copyProperties(vO, bean);
    hardwareComputingExecutorEntityRepository.save(bean);
  }

  public HardwareComputingExecutorEntityDTO getById(Long id) {
    HardwareComputingExecutorEntity original = requireOne(id);
    return toDTO(original);
  }

  public Page<HardwareComputingExecutorEntityDTO> query(HardwareComputingExecutorEntityQueryVO vO) {
    throw new UnsupportedOperationException();
  }

  private HardwareComputingExecutorEntityDTO toDTO(HardwareComputingExecutorEntity original) {
    HardwareComputingExecutorEntityDTO bean = new HardwareComputingExecutorEntityDTO();
    BeanUtils.copyProperties(original, bean);
    return bean;
  }

  private HardwareComputingExecutorEntity requireOne(Long id) {
    return hardwareComputingExecutorEntityRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
  }
}
