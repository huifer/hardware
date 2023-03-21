package com.github.huifer.hardware.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.github.huifer.hardware"})
public class HdApp {

  public static void main(String[] args) {
    SpringApplication.run(HdApp.class, args);
  }

}
