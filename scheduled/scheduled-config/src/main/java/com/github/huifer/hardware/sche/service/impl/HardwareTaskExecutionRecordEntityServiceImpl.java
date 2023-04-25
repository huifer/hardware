package com.github.huifer.hardware.sche.service.impl;


import com.github.huifer.hardware.sche.dto.HardwareTaskExecutionRecordEntityDTO;
import com.github.huifer.hardware.sche.entity.HardwareTaskExecutionRecordEntity;
import com.github.huifer.hardware.sche.repository.HardwareTaskExecutionRecordEntityRepository;
import com.github.huifer.hardware.sche.service.HardwareTaskExecutionRecordEntityService;
import com.github.huifer.hardware.sche.vo.HardwareTaskExecutionRecordEntityQueryVO;
import com.github.huifer.hardware.sche.vo.HardwareTaskExecutionRecordEntityUpdateVO;
import com.github.huifer.hardware.sche.vo.HardwareTaskExecutionRecordEntityVO;
import java.util.NoSuchElementException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class HardwareTaskExecutionRecordEntityServiceImpl implements
    HardwareTaskExecutionRecordEntityService {

  @Autowired
  private HardwareTaskExecutionRecordEntityRepository hardwareTaskExecutionRecordEntityRepository;

  public Long save(HardwareTaskExecutionRecordEntityVO vO) {
    HardwareTaskExecutionRecordEntity bean = new HardwareTaskExecutionRecordEntity();
    BeanUtils.copyProperties(vO, bean);
    bean = hardwareTaskExecutionRecordEntityRepository.save(bean);
    return bean.getId();
  }

  public void delete(Long id) {
    hardwareTaskExecutionRecordEntityRepository.deleteById(id);
  }

  public void update(Long id, HardwareTaskExecutionRecordEntityUpdateVO vO) {
    HardwareTaskExecutionRecordEntity bean = requireOne(id);
    BeanUtils.copyProperties(vO, bean);
    hardwareTaskExecutionRecordEntityRepository.save(bean);
  }

  public HardwareTaskExecutionRecordEntityDTO getById(Long id) {
    HardwareTaskExecutionRecordEntity original = requireOne(id);
    return toDTO(original);
  }

  public Page<HardwareTaskExecutionRecordEntityDTO> query(
      HardwareTaskExecutionRecordEntityQueryVO vO) {
    throw new UnsupportedOperationException();
  }

  private HardwareTaskExecutionRecordEntityDTO toDTO(HardwareTaskExecutionRecordEntity original) {
    HardwareTaskExecutionRecordEntityDTO bean = new HardwareTaskExecutionRecordEntityDTO();
    BeanUtils.copyProperties(original, bean);
    return bean;
  }

  private HardwareTaskExecutionRecordEntity requireOne(Long id) {
    return hardwareTaskExecutionRecordEntityRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
  }
}
