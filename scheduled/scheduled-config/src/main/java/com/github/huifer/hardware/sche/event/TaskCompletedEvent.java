package com.github.huifer.hardware.sche.event;

import java.time.Clock;
import org.springframework.context.ApplicationEvent;

/**
 * 任务执行完成
 **/
public class TaskCompletedEvent extends ApplicationEvent {

  public TaskCompletedEvent(Object source) {
    super(source);
  }

  public TaskCompletedEvent(Object source, Clock clock) {
    super(source, clock);
  }
}
