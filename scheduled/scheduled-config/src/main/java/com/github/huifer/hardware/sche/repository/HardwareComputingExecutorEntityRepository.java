package com.github.huifer.hardware.sche.repository;

import com.github.huifer.hardware.sche.entity.HardwareComputingExecutorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HardwareComputingExecutorEntityRepository extends
    JpaRepository<HardwareComputingExecutorEntity, Long>,
    JpaSpecificationExecutor<HardwareComputingExecutorEntity> {

}