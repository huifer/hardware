package com.github.huifer.hardware.sche.service;


import com.github.huifer.hardware.sche.dto.HardwareStaticCalcParamEntityDTO;
import com.github.huifer.hardware.sche.vo.HardwareStaticCalcParamEntityQueryVO;
import com.github.huifer.hardware.sche.vo.HardwareStaticCalcParamEntityUpdateVO;
import com.github.huifer.hardware.sche.vo.HardwareStaticCalcParamEntityVO;
import org.springframework.data.domain.Page;


public interface HardwareStaticCalcParamEntityService {


  public Long save(HardwareStaticCalcParamEntityVO vO);

  public void delete(Long id);

  public void update(Long id, HardwareStaticCalcParamEntityUpdateVO vO);

  public HardwareStaticCalcParamEntityDTO getById(Long id);

  public Page<HardwareStaticCalcParamEntityDTO> query(HardwareStaticCalcParamEntityQueryVO vO);

}
