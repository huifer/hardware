package com.github.huifer.hardware.sche.repository;

import com.github.huifer.hardware.sche.entity.HardwareTaskExecutionRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HardwareTaskExecutionRecordEntityRepository extends
    JpaRepository<HardwareTaskExecutionRecordEntity, Long>,
    JpaSpecificationExecutor<HardwareTaskExecutionRecordEntity> {

}