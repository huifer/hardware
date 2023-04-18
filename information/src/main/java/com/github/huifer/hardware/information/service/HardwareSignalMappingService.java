package com.github.huifer.hardware.information.service;

import com.github.huifer.hardware.information.dto.HardwareSignalMappingDTO;
import com.github.huifer.hardware.information.vo.HardwareSignalMappingQueryVO;
import com.github.huifer.hardware.information.vo.HardwareSignalMappingUpdateVO;
import com.github.huifer.hardware.information.vo.HardwareSignalMappingVO;
import java.util.NoSuchElementException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


public interface HardwareSignalMappingService {


  public Long save(HardwareSignalMappingVO vO);

  public void delete(Long id);

  public void update(Long id, HardwareSignalMappingUpdateVO vO) ;

  public HardwareSignalMappingDTO getById(Long id) ;

  public Page<HardwareSignalMappingDTO> query(HardwareSignalMappingQueryVO vO) ;


}
