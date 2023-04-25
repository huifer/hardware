package com.github.huifer.hardware.information.service;

import com.github.huifer.hardware.common.base.PageResponse;
import com.github.huifer.hardware.information.dto.HardwareSignalMappingDTO;
import com.github.huifer.hardware.information.vo.HardwareSignalMappingQueryVO;
import com.github.huifer.hardware.information.vo.HardwareSignalMappingUpdateVO;
import com.github.huifer.hardware.information.vo.HardwareSignalMappingVO;


public interface HardwareSignalMappingService {


    Long save(HardwareSignalMappingVO vO);

   Boolean delete(Long id);

   Boolean update(Long id, HardwareSignalMappingUpdateVO vO) ;

   HardwareSignalMappingDTO getById(Long id) ;

   PageResponse<HardwareSignalMappingDTO> query(HardwareSignalMappingQueryVO vO) ;


}
