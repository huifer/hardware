package com.github.huifer.hardware.sche.service;


import com.github.huifer.hardware.sche.dto.HardwareComputingExecutorEntityDTO;
import com.github.huifer.hardware.sche.vo.HardwareComputingExecutorEntityQueryVO;
import com.github.huifer.hardware.sche.vo.HardwareComputingExecutorEntityUpdateVO;
import com.github.huifer.hardware.sche.vo.HardwareComputingExecutorEntityVO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface HardwareComputingExecutorEntityService {



  public Long save(HardwareComputingExecutorEntityVO vO);

  public void delete(Long id) ;
  public void update(Long id, HardwareComputingExecutorEntityUpdateVO vO) ;

  public HardwareComputingExecutorEntityDTO getById(Long id) ;

  public Page<HardwareComputingExecutorEntityDTO> query(HardwareComputingExecutorEntityQueryVO vO);

;
}
