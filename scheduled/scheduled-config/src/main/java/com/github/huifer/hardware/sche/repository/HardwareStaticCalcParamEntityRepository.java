package com.github.huifer.hardware.sche.repository;

import com.github.huifer.hardware.sche.entity.HardwareStaticCalcParamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HardwareStaticCalcParamEntityRepository extends
    JpaRepository<HardwareStaticCalcParamEntity, Long>,
    JpaSpecificationExecutor<HardwareStaticCalcParamEntity> {

}