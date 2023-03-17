package com.github.huifer.hardware.listener;

import com.github.huifer.hardware.common.event.DataReceptionEvent;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class DataReceptionEventListener implements ApplicationListener<DataReceptionEvent> {

  private static final Logger logger = LoggerFactory.getLogger(DataReceptionEventListener.class);
  Gson gson = new Gson();

  @Override
  @Async
  public void onApplicationEvent(DataReceptionEvent event) {
    if (logger.isInfoEnabled()) {
      logger.info("onApplicationEvent,event = {}", gson.toJson(event));
    }
// TODO: 2023/3/16 数据发送到mq
  }
}
