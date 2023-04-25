package com.github.huifer.hardware.sche.repository;

import com.github.huifer.hardware.sche.entity.HardwareCalcParamFilterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HardwareCalcParamFilterEntityRepository extends
    JpaRepository<HardwareCalcParamFilterEntity, Long>,
    JpaSpecificationExecutor<HardwareCalcParamFilterEntity> {

}