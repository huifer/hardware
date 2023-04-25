package com.github.huifer.hardware.sche.service;


import com.github.huifer.hardware.sche.dto.HardwareComputingExecutorResultEntityDTO;
import com.github.huifer.hardware.sche.vo.HardwareComputingExecutorResultEntityQueryVO;
import com.github.huifer.hardware.sche.vo.HardwareComputingExecutorResultEntityUpdateVO;
import com.github.huifer.hardware.sche.vo.HardwareComputingExecutorResultEntityVO;
import org.springframework.data.domain.Page;


public interface HardwareComputingExecutorResultEntityService {


  public Long save(HardwareComputingExecutorResultEntityVO vO);

  public void delete(Long id);

  public void update(Long id, HardwareComputingExecutorResultEntityUpdateVO vO);

  public HardwareComputingExecutorResultEntityDTO getById(Long id);

  public Page<HardwareComputingExecutorResultEntityDTO> query(
      HardwareComputingExecutorResultEntityQueryVO vO);


}
