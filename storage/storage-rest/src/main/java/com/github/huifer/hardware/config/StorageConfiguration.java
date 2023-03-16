package com.github.huifer.hardware.config;

import com.github.huifer.hardware.sc.entity.StorageType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("hd.storage")
@Getter
@Setter
public class StorageConfiguration {


  private StorageType type =StorageType.MONGO;

}
