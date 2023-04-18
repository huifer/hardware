package com.github.huifer.hardware.information.repository;

import com.github.huifer.hardware.information.entity.DeviceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DeviceDetailRepository extends JpaRepository<DeviceDetail, Long>,
    JpaSpecificationExecutor<DeviceDetail> {

}