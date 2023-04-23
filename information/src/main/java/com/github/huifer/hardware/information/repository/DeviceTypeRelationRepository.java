package com.github.huifer.hardware.information.repository;

import com.github.huifer.hardware.information.entity.DeviceTypeRelationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DeviceTypeRelationRepository extends JpaRepository<DeviceTypeRelationEntity, Long>,
    JpaSpecificationExecutor<DeviceTypeRelationEntity> {

}