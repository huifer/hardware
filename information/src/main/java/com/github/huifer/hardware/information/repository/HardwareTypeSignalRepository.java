package com.github.huifer.hardware.information.repository;

import com.github.huifer.hardware.information.entity.HardwareTypeSignalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HardwareTypeSignalRepository extends JpaRepository<HardwareTypeSignalEntity, Long>,
    JpaSpecificationExecutor<HardwareTypeSignalEntity> {

}