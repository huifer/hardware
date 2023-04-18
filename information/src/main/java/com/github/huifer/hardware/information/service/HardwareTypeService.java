package com.github.huifer.hardware.information.service;


import com.github.huifer.hardware.information.vo.HardwareTypeDTO;
import com.github.huifer.hardware.information.vo.HardwareTypeQueryVO;
import com.github.huifer.hardware.information.vo.HardwareTypeUpdateVO;
import com.github.huifer.hardware.information.vo.HardwareTypeVO;
import java.util.NoSuchElementException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

public interface HardwareTypeService {



  public Long save(HardwareTypeVO vO) ;

  public void delete(Long id);
  public void update(Long id, HardwareTypeUpdateVO vO);

  public HardwareTypeDTO getById(Long id) ;

  public Page<HardwareTypeDTO> query(HardwareTypeQueryVO vO) ;


}
