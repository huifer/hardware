package com.github.huifer.hardware.information.repository;

import com.github.huifer.hardware.information.entity.HardwareDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HardwareDetailRepository extends JpaRepository<HardwareDetail, Long>,
    JpaSpecificationExecutor<HardwareDetail> {

}