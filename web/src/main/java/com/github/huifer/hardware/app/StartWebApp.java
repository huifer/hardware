package com.github.huifer.hardware.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = {"com.github.huifer.hardware"})
@EnableMongoRepositories(basePackages = {"com.github.huifer.hardware"})
public class StartWebApp {

  public static void main(String[] args) {
    SpringApplication.run(StartWebApp.class, args);
  }

}
