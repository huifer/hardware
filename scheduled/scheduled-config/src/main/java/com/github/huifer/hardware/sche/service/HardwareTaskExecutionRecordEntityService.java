package com.github.huifer.hardware.sche.service;


import com.github.huifer.hardware.sche.dto.HardwareTaskExecutionRecordEntityDTO;
import com.github.huifer.hardware.sche.vo.HardwareTaskExecutionRecordEntityQueryVO;
import com.github.huifer.hardware.sche.vo.HardwareTaskExecutionRecordEntityUpdateVO;
import com.github.huifer.hardware.sche.vo.HardwareTaskExecutionRecordEntityVO;
import org.springframework.data.domain.Page;

public interface HardwareTaskExecutionRecordEntityService {


  public Long save(HardwareTaskExecutionRecordEntityVO vO) ;

  public void delete(Long id);

  public void update(Long id, HardwareTaskExecutionRecordEntityUpdateVO vO) ;

  public HardwareTaskExecutionRecordEntityDTO getById(Long id) ;

  public Page<HardwareTaskExecutionRecordEntityDTO> query(
      HardwareTaskExecutionRecordEntityQueryVO vO);


}
