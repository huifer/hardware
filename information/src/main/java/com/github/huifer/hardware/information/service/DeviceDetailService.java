package com.github.huifer.hardware.information.service;


import com.github.huifer.hardware.information.dto.DeviceDetailDTO;
import com.github.huifer.hardware.information.vo.DeviceDetailQueryVO;
import com.github.huifer.hardware.information.vo.DeviceDetailUpdateVO;
import com.github.huifer.hardware.information.vo.DeviceDetailVO;
import org.springframework.data.domain.Page;

public interface DeviceDetailService {


  public Long save(DeviceDetailVO vO) ;

  public void delete(Long id) ;

  public void update(Long id, DeviceDetailUpdateVO vO) ;

  public DeviceDetailDTO getById(Long id) ;

  public Page<DeviceDetailDTO> query(DeviceDetailQueryVO vO);

}
