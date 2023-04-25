package com.github.huifer.hardware.information.service;


import com.github.huifer.hardware.information.vo.HardwareTypeDTO;
import com.github.huifer.hardware.information.vo.HardwareTypeUpdateVO;
import com.github.huifer.hardware.information.vo.HardwareTypeVO;
import java.util.List;

public interface HardwareTypeService {


  /**
   * 添加硬件类型
   * @param vO
   * @return
   */
  public Long save(HardwareTypeVO vO) ;

  /**
   * 删除 设备类型
   * @param id
   * @return
   */
  public Boolean delete(Long id);

  /**
   * 修改硬件类型
   * @param id
   * @param vO
   * @return
   */
  public Boolean update(Long id, HardwareTypeUpdateVO vO);

  /**
   * 获取硬件类型
   * @param id
   * @return
   */
  public HardwareTypeDTO getById(Long id) ;

  /**
   * 获取所有的硬件类型
   * @return
   */
  public List<HardwareTypeDTO> query() ;


}
