package com.github.huifer.hardware.information.service.impl;

import com.github.huifer.hardware.information.entity.HardwareTypeEntity;
import com.github.huifer.hardware.information.repository.HardwareTypeRepository;
import com.github.huifer.hardware.information.service.HardwareTypeService;
import com.github.huifer.hardware.information.vo.HardwareTypeDTO;
import com.github.huifer.hardware.information.vo.HardwareTypeUpdateVO;
import com.github.huifer.hardware.information.vo.HardwareTypeVO;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HardwareTypeServiceImpl implements HardwareTypeService {

  @Autowired
  private HardwareTypeRepository hardwareTypeRepository;

  @Transactional(rollbackFor = {Exception.class})
  public Long save(HardwareTypeVO vO) {
    HardwareTypeEntity bean = new HardwareTypeEntity();
    bean.setName(vO.getName());
    bean.setCode(vO.getCode());
    bean.setState(vO.getState());
    bean.setCreateTime(LocalDateTime.now());
    bean.setDeleted(false);
    bean = hardwareTypeRepository.save(bean);
    return bean.getId();
  }

  @Transactional(rollbackFor = {Exception.class})
  public Boolean delete(Long id) {
    HardwareTypeEntity hardwareType = requireOne(id);
    hardwareType.setDeleted(true);
    hardwareType.setUpdateTime(LocalDateTime.now());
    return hardwareTypeRepository.save(hardwareType) != null;
  }

  @Transactional(rollbackFor = {Exception.class})
  public Boolean update(Long id, HardwareTypeUpdateVO vO) {
    HardwareTypeEntity hardwareType = requireOne(id);
    hardwareType.setUpdateTime(LocalDateTime.now());
    hardwareType.setCode(vO.getCode());
    hardwareType.setState(vO.getState());
    hardwareType.setName(vO.getName());
    return hardwareTypeRepository.save(hardwareType) == null;
  }

  public HardwareTypeDTO getById(Long id) {
    Optional<HardwareTypeEntity> byId = hardwareTypeRepository.findById(id);
    HardwareTypeEntity original = new HardwareTypeEntity();
    if (byId.isPresent()) {
      original = byId.get();
    }
    return toDTO(original);
  }

  public List<HardwareTypeDTO> query() {
    List<HardwareTypeEntity> typeList = hardwareTypeRepository.findAll();
    List<HardwareTypeDTO> hardwareTypeDTOList = typeList.stream().map(this::toDTO)
        .collect(Collectors.toList());
    return hardwareTypeDTOList;
  }

  private HardwareTypeDTO toDTO(HardwareTypeEntity original) {
    HardwareTypeDTO bean = new HardwareTypeDTO();
    BeanUtils.copyProperties(original, bean);
    return bean;
  }

  private HardwareTypeEntity requireOne(Long id) {
    return hardwareTypeRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("此数据不存在请刷新: " + id));
  }
}
