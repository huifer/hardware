package com.github.huifer.hardware.information.service;


import com.github.huifer.hardware.information.dto.HardwareTypeSignalDTO;
import com.github.huifer.hardware.information.vo.HardwareTypeSignalUpdateVO;
import com.github.huifer.hardware.information.vo.HardwareTypeSignalVO;


public interface HardwareTypeSignalService {


  /**
   * 添加硬件信号类型和硬件类型
   * @param vO 数据值
   * @return Long
   */
   Long save(HardwareTypeSignalVO vO);

  /**
   *  删除指定条件的硬件类型信息
    * @param id id
   * @return Boolean
   */
   Boolean delete(Long id);

  /**
   * 修改条件的硬件类型信息
   * @param vO 数据值
   * @param id id
   * @return Boolean
   */
   Boolean update(Long id, HardwareTypeSignalUpdateVO vO);

   HardwareTypeSignalDTO getById(Long id) ;


}
