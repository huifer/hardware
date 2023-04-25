package com.github.huifer.hardware.information.repository;

import com.github.huifer.hardware.information.entity.HardwareSignalMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HardwareSignalMappingRepository extends JpaRepository<HardwareSignalMapping, Long>,
    JpaSpecificationExecutor<HardwareSignalMapping> {

}