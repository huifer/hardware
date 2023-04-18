package com.github.huifer.hardware.information.service.impl;

import com.github.huifer.hardware.information.dto.DeviceDetailDTO;
import com.github.huifer.hardware.information.entity.DeviceDetail;
import com.github.huifer.hardware.information.repository.DeviceDetailRepository;
import com.github.huifer.hardware.information.service.DeviceDetailService;
import com.github.huifer.hardware.information.vo.DeviceDetailQueryVO;
import com.github.huifer.hardware.information.vo.DeviceDetailUpdateVO;
import com.github.huifer.hardware.information.vo.DeviceDetailVO;
import java.util.NoSuchElementException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class DeviceDetailServiceImpl implements DeviceDetailService {

  @Autowired
  private DeviceDetailRepository deviceDetailRepository;

  public Long save(DeviceDetailVO vO) {
    DeviceDetail bean = new DeviceDetail();
    BeanUtils.copyProperties(vO, bean);
    bean = deviceDetailRepository.save(bean);
    return bean.getId();
  }

  public void delete(Long id) {
    deviceDetailRepository.deleteById(id);
  }

  public void update(Long id, DeviceDetailUpdateVO vO) {
    DeviceDetail bean = requireOne(id);
    BeanUtils.copyProperties(vO, bean);
    deviceDetailRepository.save(bean);
  }

  public DeviceDetailDTO getById(Long id) {
    DeviceDetail original = requireOne(id);
    return toDTO(original);
  }

  public Page<DeviceDetailDTO> query(DeviceDetailQueryVO vO) {
    throw new UnsupportedOperationException();
  }

  private DeviceDetailDTO toDTO(DeviceDetail original) {
    DeviceDetailDTO bean = new DeviceDetailDTO();
    BeanUtils.copyProperties(original, bean);
    return bean;
  }

  private DeviceDetail requireOne(Long id) {
    return deviceDetailRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
  }
}
