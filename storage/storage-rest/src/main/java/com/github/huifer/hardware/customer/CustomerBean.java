package com.github.huifer.hardware.customer;

import com.github.huifer.hardware.config.StorageConfiguration;
import com.github.huifer.hardware.sc.entity.StorageType;
import com.github.huifer.hardware.sc.service.SignalDocumentService;
import com.github.huifer.hardware.sm.service.impl.SignalDocumentServiceMongoImpl;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class CustomerBean {


  @Value("${hd.storage.type}")
  private String data;
  @Autowired
  private StorageConfiguration storageConfiguration;

  @Bean
  public SignalDocumentService signalDocumentServiceMongo(
      @Autowired MongoTemplate mongoTemplate,
      @Autowired StorageConfiguration storageConfiguration
  ) {
    StorageType type = storageConfiguration.getType();
    if (Objects.requireNonNull(type) == StorageType.MONGO) {
      return new SignalDocumentServiceMongoImpl(mongoTemplate);
    } else {
      return SignalDocumentService.DEFAULT_SignalDocumentService;
    }
  }
}
