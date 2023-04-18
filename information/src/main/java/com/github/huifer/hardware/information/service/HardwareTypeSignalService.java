package com.github.huifer.hardware.information.service;


import com.github.huifer.hardware.information.dto.HardwareTypeSignalDTO;
import com.github.huifer.hardware.information.vo.HardwareTypeSignalQueryVO;
import com.github.huifer.hardware.information.vo.HardwareTypeSignalUpdateVO;
import com.github.huifer.hardware.information.vo.HardwareTypeSignalVO;
import org.springframework.data.domain.Page;


public interface HardwareTypeSignalService {



  public Long save(HardwareTypeSignalVO vO);

  public void delete(Long id);

  public void update(Long id, HardwareTypeSignalUpdateVO vO);

  public HardwareTypeSignalDTO getById(Long id) ;

  public Page<HardwareTypeSignalDTO> query(HardwareTypeSignalQueryVO vO) ;

}
