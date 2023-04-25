package com.github.huifer.hardware.sche.service;


import com.github.huifer.hardware.sche.dto.HardwareCalcParamsEntityDTO;
import com.github.huifer.hardware.sche.vo.HardwareCalcParamsEntityQueryVO;
import com.github.huifer.hardware.sche.vo.HardwareCalcParamsEntityUpdateVO;
import com.github.huifer.hardware.sche.vo.HardwareCalcParamsEntityVO;
import org.springframework.data.domain.Page;

public interface HardwareCalcParamsEntityService {


  public Long save(HardwareCalcParamsEntityVO vO);

  public void delete(Long id);

  public void update(Long id, HardwareCalcParamsEntityUpdateVO vO);

  public HardwareCalcParamsEntityDTO getById(Long id);

  public Page<HardwareCalcParamsEntityDTO> query(HardwareCalcParamsEntityQueryVO vO);

}
