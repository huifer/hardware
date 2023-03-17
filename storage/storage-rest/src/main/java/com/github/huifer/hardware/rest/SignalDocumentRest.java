package com.github.huifer.hardware.rest;

import com.github.huifer.hardware.common.event.DataReceptionEvent;
import com.github.huifer.hardware.common.enums.ReduceTypeEnums;
import com.github.huifer.hardware.sc.entity.SignQueryResponse;
import com.github.huifer.hardware.sc.entity.SignalDocument;
import com.github.huifer.hardware.sc.entity.SignalQuery;
import com.github.huifer.hardware.sc.service.SignalDocumentService;
import com.google.gson.Gson;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signal")
public class SignalDocumentRest {

  @Autowired
  private SignalDocumentService signalDocumentService;


  @GetMapping("/query")
  public ResponseEntity<Map<String, List<SignalDocument>>> query(SignalQuery signalQuery) {
    return ResponseEntity.ok(this.signalDocumentService.query(signalQuery));
  }

  Gson gson = new Gson();
  @Autowired
  private ApplicationEventPublisher eventPublisher;

  /**
   * http://localhost:9011/signal/querySingWithRange?deviceId=device_2&keys=sig_3&keys=sig_1&keys=sig_2&startTime=1678693692974&endTime=1678693702974&reduceTypeEnums=AVG&sec=1&deviceType=YEWEI
   */
  @GetMapping("/querySingWithRange")
  public ResponseEntity<Map<String, Map<Integer, SignQueryResponse>>> querySingWithRange(
      SignalQuery signalQuery,
      ReduceTypeEnums reduceTypeEnums,
      int sec
  ) {
    return ResponseEntity.ok(this.signalDocumentService.querySingWithRange(
        signalQuery,
        reduceTypeEnums,
        sec
    ));

  }

  @PostMapping("/save")
  public ResponseEntity<Boolean> save(
      @RequestBody SignalDocument signalDocument
  ) {

    this.signalDocumentService.save(signalDocument);
    eventPublisher.publishEvent(new DataReceptionEvent(
        gson.toJson(signalDocument),
        signalDocument.getDeviceId(),
        signalDocument.getDeviceType()
    ));
    return ResponseEntity.ok(true);
  }


}
