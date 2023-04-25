package com.github.huifer.hardware.sche.repository;

import com.github.huifer.hardware.sche.entity.HardwareCalcParamMappingQueryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HardwareCalcParamMappingQueryEntityRepository extends
    JpaRepository<HardwareCalcParamMappingQueryEntity, Long>,
    JpaSpecificationExecutor<HardwareCalcParamMappingQueryEntity> {

}