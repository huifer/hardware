package com.github.huifer.hardware.sche.service;


import com.github.huifer.hardware.sche.dto.HardwareCalcParamMappingQueryTimeEntityDTO;
import com.github.huifer.hardware.sche.vo.HardwareCalcParamMappingQueryTimeEntityQueryVO;
import com.github.huifer.hardware.sche.vo.HardwareCalcParamMappingQueryTimeEntityUpdateVO;
import com.github.huifer.hardware.sche.vo.HardwareCalcParamMappingQueryTimeEntityVO;
import org.springframework.data.domain.Page;

public interface HardwareCalcParamMappingQueryTimeEntityService {

  public Long save(HardwareCalcParamMappingQueryTimeEntityVO vO);

  public void delete(Long id);

  public void update(Long id, HardwareCalcParamMappingQueryTimeEntityUpdateVO vO);

  public HardwareCalcParamMappingQueryTimeEntityDTO getById(Long id);

  public Page<HardwareCalcParamMappingQueryTimeEntityDTO> query(
      HardwareCalcParamMappingQueryTimeEntityQueryVO vO);

  ;
}
