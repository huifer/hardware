package com.github.huifer.hardware.information.service;


import com.github.huifer.hardware.information.dto.HardwareSignalDTO;
import com.github.huifer.hardware.information.vo.HardwareSignalQueryVO;
import com.github.huifer.hardware.information.vo.HardwareSignalUpdateVO;
import com.github.huifer.hardware.information.vo.HardwareSignalVO;
import java.util.NoSuchElementException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

public interface HardwareSignalService {


  public Long save(HardwareSignalVO vO) ;

  public void delete(Long id);
  public void update(Long id, HardwareSignalUpdateVO vO) ;

  public HardwareSignalDTO getById(Long id) ;

  public Page<HardwareSignalDTO> query(HardwareSignalQueryVO vO);


}
