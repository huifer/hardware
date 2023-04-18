package com.github.huifer.hardware.information.repository;

import com.github.huifer.hardware.information.entity.HardwareType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HardwareTypeRepository extends JpaRepository<HardwareType, Long>,
    JpaSpecificationExecutor<HardwareType> {

}