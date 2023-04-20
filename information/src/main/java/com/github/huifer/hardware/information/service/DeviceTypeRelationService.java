package com.github.huifer.hardware.information.service;


import com.github.huifer.hardware.information.dto.DeviceTypeRelationDTO;
import com.github.huifer.hardware.information.vo.DeviceTypeRelationUpdateVO;
import com.github.huifer.hardware.information.vo.DeviceTypeRelationVO;

public interface DeviceTypeRelationService {

 /**
  * 添加设备类别和对应的设备关联
  *
  * @param vO 数据值
  * @return 添加是否成功
  */
 Long save(DeviceTypeRelationVO vO);


 /**
  * 删除
  *
  * @param id id
  * @return
  */
 Boolean delete(Long id);

 /**
  * 修改
  *
  * @param id id
  * @param vO 数据
  * @return
  */
 Boolean update(Long id, DeviceTypeRelationUpdateVO vO);

 /**
  * 查询所有设备类别和对应的设备关联
  *
  * @param id
  * @return
  */
 DeviceTypeRelationDTO getById(Long id);


}
