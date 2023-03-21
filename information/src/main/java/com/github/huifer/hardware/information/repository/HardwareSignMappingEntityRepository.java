package com.github.huifer.hardware.information.repository;

import com.github.huifer.hardware.information.entity.HardwareSignMappingEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HardwareSignMappingEntityRepository extends
    MongoRepository<HardwareSignMappingEntity, String> {

}
