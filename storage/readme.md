# 设备数据存储
本项目用于存储硬件生产的数据，数据采集方式有两种：
1. 硬件供应商直接与本项目对接
2. 本项目定时拉取外部数据







```

<details>
<summary>启动类</summary>
<br>

```
package com.example.demo;

import java.util.function.Consumer;
import java.util.function.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableScheduling
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @GetMapping("/beat")
  public String beat(){
    return "beat";
  }
  @Bean
  public Consumer<String> consumer() {
    return message -> {
      System.out.println("received " + message);
    };
  }
  @Autowired
  private StreamBridge streamBridge;
  @Scheduled(cron = "*/2 * * * * *")
  public void sendMessage(){
    streamBridge.send("producer-out-0"," jack from Stream bridge");
  }
}


```

</details>
```