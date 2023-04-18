package com.github.huifer.hardware.information.service;

import com.github.huifer.hardware.information.dto.HardwareTypeSignalIdentifierDTO;
import com.github.huifer.hardware.information.vo.HardwareTypeSignalIdentifierQueryVO;
import com.github.huifer.hardware.information.vo.HardwareTypeSignalIdentifierUpdateVO;
import com.github.huifer.hardware.information.vo.HardwareTypeSignalIdentifierVO;
import org.springframework.data.domain.Page;

public interface HardwareTypeSignalIdentifierService {


  public Long save(HardwareTypeSignalIdentifierVO vO) ;

  public void delete(Long id) ;

  public void update(Long id, HardwareTypeSignalIdentifierUpdateVO vO);

  public HardwareTypeSignalIdentifierDTO getById(Long id) ;

  public Page<HardwareTypeSignalIdentifierDTO> query(HardwareTypeSignalIdentifierQueryVO vO) ;


}
