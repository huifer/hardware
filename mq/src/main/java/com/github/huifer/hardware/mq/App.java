package com.github.huifer.hardware.mq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class App {

  public static void main(String[] args) throws InterruptedException {
    SpringApplication.run(App.class, args);
    Thread.sleep(1000000L);
  }

}
