package com.github.huifer.hardware.information.repository;

import com.github.huifer.hardware.information.entity.HardwareTypeSignalIdentifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HardwareTypeSignalIdentifierRepository extends
    JpaRepository<HardwareTypeSignalIdentifier, Long>,
    JpaSpecificationExecutor<HardwareTypeSignalIdentifier> {

}