package com.github.huifer.hardware.sche.service.impl;


import com.github.huifer.hardware.sche.dto.HardwareOperationRuleEntityDTO;
import com.github.huifer.hardware.sche.entity.HardwareOperationRuleEntity;
import com.github.huifer.hardware.sche.repository.HardwareOperationRuleEntityRepository;
import com.github.huifer.hardware.sche.service.HardwareOperationRuleEntityService;
import com.github.huifer.hardware.sche.vo.HardwareOperationRuleEntityQueryVO;
import com.github.huifer.hardware.sche.vo.HardwareOperationRuleEntityUpdateVO;
import com.github.huifer.hardware.sche.vo.HardwareOperationRuleEntityVO;
import java.util.NoSuchElementException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class HardwareOperationRuleEntityServiceImpl implements HardwareOperationRuleEntityService {

  @Autowired
  private HardwareOperationRuleEntityRepository hardwareOperationRuleEntityRepository;

  public Long save(HardwareOperationRuleEntityVO vO) {
    HardwareOperationRuleEntity bean = new HardwareOperationRuleEntity();
    BeanUtils.copyProperties(vO, bean);
    bean = hardwareOperationRuleEntityRepository.save(bean);
    return bean.getId();
  }

  public void delete(Long id) {
    hardwareOperationRuleEntityRepository.deleteById(id);
  }

  public void update(Long id, HardwareOperationRuleEntityUpdateVO vO) {
    HardwareOperationRuleEntity bean = requireOne(id);
    BeanUtils.copyProperties(vO, bean);
    hardwareOperationRuleEntityRepository.save(bean);
  }

  public HardwareOperationRuleEntityDTO getById(Long id) {
    HardwareOperationRuleEntity original = requireOne(id);
    return toDTO(original);
  }

  public Page<HardwareOperationRuleEntityDTO> query(HardwareOperationRuleEntityQueryVO vO) {
    throw new UnsupportedOperationException();
  }

  private HardwareOperationRuleEntityDTO toDTO(HardwareOperationRuleEntity original) {
    HardwareOperationRuleEntityDTO bean = new HardwareOperationRuleEntityDTO();
    BeanUtils.copyProperties(original, bean);
    return bean;
  }

  private HardwareOperationRuleEntity requireOne(Long id) {
    return hardwareOperationRuleEntityRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
  }
}
