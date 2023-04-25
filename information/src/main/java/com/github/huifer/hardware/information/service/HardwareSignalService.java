package com.github.huifer.hardware.information.service;


import com.github.huifer.hardware.common.base.PageResponse;
import com.github.huifer.hardware.information.dto.HardwareSignalDTO;
import com.github.huifer.hardware.information.vo.HardwareSignalQueryVO;
import com.github.huifer.hardware.information.vo.HardwareSignalUpdateVO;
import com.github.huifer.hardware.information.vo.HardwareSignalVO;

public interface HardwareSignalService {


  /**
   * 添加信号量信息
   * @param vO 添加的数据
   * @return 返回结果
   */
   Long save(HardwareSignalVO vO) ;

  /**
   * 删除信号量信息
   * @param id id
   * @return 结果
   */
   Boolean delete(Long id);

  /**
   * 修改信号量信息
   * @param vO 更新的数据
   * @return 结果
   */
   Boolean update(Long id, HardwareSignalUpdateVO vO) ;

  /**
   * 获取信号量信息
   * @param id
   * @return
   */
   HardwareSignalDTO getById(Long id) ;

  /**
   * 获取所有信号量信息
   * @param vO
   * @return
   */
   PageResponse<HardwareSignalDTO> query(HardwareSignalQueryVO vO);


}
