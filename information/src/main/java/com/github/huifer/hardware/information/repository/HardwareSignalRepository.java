package com.github.huifer.hardware.information.repository;

import com.github.huifer.hardware.information.entity.HardwareSignalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HardwareSignalRepository extends JpaRepository<HardwareSignalEntity, Long>,
    JpaSpecificationExecutor<HardwareSignalEntity> {

}