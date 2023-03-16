package com.github.huifer.hardware.rest;

import com.github.huifer.hardware.sc.entity.ReduceTypeEnums;
import com.github.huifer.hardware.sc.entity.SignQueryResponse;
import com.github.huifer.hardware.sc.entity.SignalDocument;
import com.github.huifer.hardware.sc.entity.SignalQuery;
import com.github.huifer.hardware.sc.service.SignalDocumentService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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


}
