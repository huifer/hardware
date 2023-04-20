package com.github.huifer.hardware.information.service;


import com.github.huifer.hardware.common.base.PageResponse;
import com.github.huifer.hardware.information.dto.HardwareDetailDTO;
import com.github.huifer.hardware.information.vo.HardwareDetailQueryVO;
import com.github.huifer.hardware.information.vo.HardwareDetailUpdateVO;
import com.github.huifer.hardware.information.vo.HardwareDetailVO;

public interface HardwareDetailService {


  /**
   * 添加设备详情
   *
   * @param vO 设备详情数据
   * @return 添加结果
   */
   Long save(HardwareDetailVO vO);

  /**
   * 删除设备详情
   *
   * @param id 设备编号
   * @return 删除结果
   */
   Boolean delete(Long id);

  /**
   * 修改设备详情
   *
   * @param id 设备编号
   * @param vO 设备详情数据
   * @return 修改结果
   */
   Boolean update(Long id, HardwareDetailUpdateVO vO);

  /**
   * 获取设备详情
   *
   * @param id 设备编号
   * @return 设备详情数据
   */
   HardwareDetailDTO getById(Long id);

  /**
   * 获取所有设备详情
   *
   * @param vO 查询条件
   * @return 设备详情数据列表
   */
  PageResponse<HardwareDetailDTO> query(HardwareDetailQueryVO vO);

}
