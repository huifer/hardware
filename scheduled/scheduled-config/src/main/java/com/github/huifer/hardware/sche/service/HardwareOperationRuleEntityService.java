package com.github.huifer.hardware.sche.service;


import com.github.huifer.hardware.sche.dto.HardwareOperationRuleEntityDTO;
import com.github.huifer.hardware.sche.vo.HardwareOperationRuleEntityQueryVO;
import com.github.huifer.hardware.sche.vo.HardwareOperationRuleEntityUpdateVO;
import com.github.huifer.hardware.sche.vo.HardwareOperationRuleEntityVO;
import org.springframework.data.domain.Page;

public interface HardwareOperationRuleEntityService {


  public Long save(HardwareOperationRuleEntityVO vO);

  public void delete(Long id);

  public void update(Long id, HardwareOperationRuleEntityUpdateVO vO);

  public HardwareOperationRuleEntityDTO getById(Long id);

  public Page<HardwareOperationRuleEntityDTO> query(HardwareOperationRuleEntityQueryVO vO);

}
