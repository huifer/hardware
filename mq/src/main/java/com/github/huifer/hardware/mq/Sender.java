package com.github.huifer.hardware.mq;

import java.util.function.Consumer;
import java.util.function.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class Sender {
  @Autowired
  private StreamBridge streamBridge;
  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  @Scheduled(cron = "*/2 * * * * *")
  public void sendMessage(){
    streamBridge.send("producer-out-0"," jack from Stream bridge");
  }

//  @Scheduled(cron = "*/2 * * * * *")
//  public void sendMessage2(){
//    kafkaTemplate.send("kafkaTopic", "message");
//  }


  @Bean
  public Consumer<String> consumer() {
    return message -> {
      System.out.println("received " + message);
    };
  }


}
