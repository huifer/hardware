package com.github.huifer.hardware.information.repository;

import com.github.huifer.hardware.information.entity.HardwareTypeSignal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HardwareTypeSignalRepository extends JpaRepository<HardwareTypeSignal, Long>,
    JpaSpecificationExecutor<HardwareTypeSignal> {

}