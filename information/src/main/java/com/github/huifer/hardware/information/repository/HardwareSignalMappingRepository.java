package com.github.huifer.hardware.information.repository;

import com.github.huifer.hardware.information.entity.HardwareSignalMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HardwareSignalMappingRepository extends JpaRepository<HardwareSignalMappingEntity, Long>,
    JpaSpecificationExecutor<HardwareSignalMappingEntity> {

}