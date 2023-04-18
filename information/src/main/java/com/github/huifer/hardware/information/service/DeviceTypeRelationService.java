package com.github.huifer.hardware.information.service;


import com.github.huifer.hardware.information.dto.DeviceTypeRelationDTO;
import com.github.huifer.hardware.information.vo.DeviceTypeRelationQueryVO;
import com.github.huifer.hardware.information.vo.DeviceTypeRelationUpdateVO;
import com.github.huifer.hardware.information.vo.DeviceTypeRelationVO;

import org.springframework.data.domain.Page;

public interface DeviceTypeRelationService  {


  public Long save(DeviceTypeRelationVO vO) ;

  public void delete(Long id) ;

  public void update(Long id, DeviceTypeRelationUpdateVO vO) ;

  public DeviceTypeRelationDTO getById(Long id) ;

  public Page<DeviceTypeRelationDTO> query(DeviceTypeRelationQueryVO vO) ;

}
