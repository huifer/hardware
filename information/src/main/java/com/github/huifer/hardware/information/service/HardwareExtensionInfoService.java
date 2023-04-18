package com.github.huifer.hardware.information.service;

import com.github.huifer.hardware.information.dto.HardwareExtensionInfoDTO;
import com.github.huifer.hardware.information.vo.HardwareExtensionInfoQueryVO;
import com.github.huifer.hardware.information.vo.HardwareExtensionInfoUpdateVO;
import com.github.huifer.hardware.information.vo.HardwareExtensionInfoVO;
import java.util.NoSuchElementException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


public interface HardwareExtensionInfoService {


  public Long save(HardwareExtensionInfoVO vO) ;

  public void delete(Long id) ;

  public void update(Long id, HardwareExtensionInfoUpdateVO vO);

  public HardwareExtensionInfoDTO getById(Long id);

  public Page<HardwareExtensionInfoDTO> query(HardwareExtensionInfoQueryVO vO);


}
