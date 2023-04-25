package com.github.huifer.hardware.sche.repository;

import com.github.huifer.hardware.sche.entity.HardwareComputingExecutorResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HardwareComputingExecutorResultEntityRepository extends
    JpaRepository<HardwareComputingExecutorResultEntity, Long>,
    JpaSpecificationExecutor<HardwareComputingExecutorResultEntity> {

}