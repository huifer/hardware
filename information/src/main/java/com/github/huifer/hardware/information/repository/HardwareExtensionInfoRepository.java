package com.github.huifer.hardware.information.repository;

import com.github.huifer.hardware.information.entity.HardwareExtensionInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HardwareExtensionInfoRepository extends JpaRepository<HardwareExtensionInfoEntity, Long>,
    JpaSpecificationExecutor<HardwareExtensionInfoEntity> {

}