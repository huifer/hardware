package com.github.huifer.hardware.information.repository;

import com.github.huifer.hardware.information.entity.HardwareTypeSignalIdentifierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HardwareTypeSignalIdentifierRepository extends
    JpaRepository<HardwareTypeSignalIdentifierEntity, Long>,
    JpaSpecificationExecutor<HardwareTypeSignalIdentifierEntity> {

}