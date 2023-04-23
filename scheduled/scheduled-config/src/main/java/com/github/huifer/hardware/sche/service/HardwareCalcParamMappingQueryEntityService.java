package com.github.huifer.hardware.sche.service;


import com.github.huifer.hardware.sche.dto.HardwareCalcParamMappingQueryEntityDTO;
import com.github.huifer.hardware.sche.vo.HardwareCalcParamMappingQueryEntityQueryVO;
import com.github.huifer.hardware.sche.vo.HardwareCalcParamMappingQueryEntityUpdateVO;
import com.github.huifer.hardware.sche.vo.HardwareCalcParamMappingQueryEntityVO;
import org.springframework.data.domain.Page;


public interface HardwareCalcParamMappingQueryEntityService {


  public Long save(HardwareCalcParamMappingQueryEntityVO vO);

  public void delete(Long id);

  public void update(Long id, HardwareCalcParamMappingQueryEntityUpdateVO vO);

  public HardwareCalcParamMappingQueryEntityDTO getById(Long id);

  public Page<HardwareCalcParamMappingQueryEntityDTO> query(
      HardwareCalcParamMappingQueryEntityQueryVO vO);

}
