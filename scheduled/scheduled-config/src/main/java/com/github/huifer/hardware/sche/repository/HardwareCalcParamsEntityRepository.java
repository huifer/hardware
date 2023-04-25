package com.github.huifer.hardware.sche.repository;

import com.github.huifer.hardware.sche.entity.HardwareCalcParamsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HardwareCalcParamsEntityRepository extends
    JpaRepository<HardwareCalcParamsEntity, Long>,
    JpaSpecificationExecutor<HardwareCalcParamsEntity> {

}