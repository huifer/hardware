package com.github.huifer.hardware.information.service;

import com.github.huifer.hardware.information.dto.HardwareExtensionInfoDTO;
import com.github.huifer.hardware.information.vo.HardwareExtensionInfoUpdateVO;
import com.github.huifer.hardware.information.vo.HardwareExtensionInfoVO;


public interface HardwareExtensionInfoService {

  /**
   *  硬件扩展信息保存
   * @param vO  保存的数据
   * @return 返回的结果
   */
   Long save(HardwareExtensionInfoVO vO) ;

  /**
   * 删除硬件扩展信息
   * @param id 要删除的数据的id
   * @return 返回的结果
   */
   Boolean delete(Long id) ;


  /**
   * 修改硬件扩展信息
   * @param vO 要更新的数据
   * @return 返回的结果
   */
   Boolean update(Long id, HardwareExtensionInfoUpdateVO vO);


  /**
   *  通过id获取数据
   * @param id 要获取的数据的id
   * @return 返回的数据
   */
   HardwareExtensionInfoDTO getById(Long id);


}
