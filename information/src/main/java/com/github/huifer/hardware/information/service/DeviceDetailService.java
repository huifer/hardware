package com.github.huifer.hardware.information.service;


import com.github.huifer.hardware.common.base.PageResponse;
import com.github.huifer.hardware.information.dto.DeviceDetailDTO;
import com.github.huifer.hardware.information.vo.DeviceDetailQueryVO;
import com.github.huifer.hardware.information.vo.DeviceDetailUpdateVO;
import com.github.huifer.hardware.information.vo.DeviceDetailVO;

public interface DeviceDetailService {


  /**
   * 添加设备详情
   *
   * @param vO 设备详情数据
   * @return 添加结果
   */
   Long save(DeviceDetailVO vO);

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
   Boolean update(Long id, DeviceDetailUpdateVO vO);

  /**
   * 获取设备详情
   *
   * @param id 设备编号
   * @return 设备详情数据
   */
   DeviceDetailDTO getById(Long id);

  /**
   * 获取所有设备详情
   *
   * @param vO 查询条件
   * @return 设备详情数据列表
   */
  PageResponse<DeviceDetailDTO> query(DeviceDetailQueryVO vO);

}
