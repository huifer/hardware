package com.github.huifer.hardware.information.repository;

import com.github.huifer.hardware.information.entity.HardwareInfoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HardwareInfoEntityRepository extends
    MongoRepository<HardwareInfoEntity, String> {

}
