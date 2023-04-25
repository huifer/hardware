package com.github.huifer.hardware.sche.repository;

import com.github.huifer.hardware.sche.entity.HardwareCalcParamMappingQueryTimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HardwareCalcParamMappingQueryTimeEntityRepository extends
    JpaRepository<HardwareCalcParamMappingQueryTimeEntity, Long>,
    JpaSpecificationExecutor<HardwareCalcParamMappingQueryTimeEntity> {

}