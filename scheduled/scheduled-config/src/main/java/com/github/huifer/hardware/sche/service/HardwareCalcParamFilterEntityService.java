package com.github.huifer.hardware.sche.service;


import com.github.huifer.hardware.sche.dto.HardwareCalcParamFilterEntityDTO;
import com.github.huifer.hardware.sche.vo.HardwareCalcParamFilterEntityQueryVO;
import com.github.huifer.hardware.sche.vo.HardwareCalcParamFilterEntityUpdateVO;
import com.github.huifer.hardware.sche.vo.HardwareCalcParamFilterEntityVO;
import org.springframework.data.domain.Page;

public interface HardwareCalcParamFilterEntityService {

  public Long save(HardwareCalcParamFilterEntityVO vO) ;

  public void delete(Long id) ;

  public void update(Long id, HardwareCalcParamFilterEntityUpdateVO vO) ;

  public HardwareCalcParamFilterEntityDTO getById(Long id) ;

  public Page<HardwareCalcParamFilterEntityDTO> query(HardwareCalcParamFilterEntityQueryVO vO) ;

}
