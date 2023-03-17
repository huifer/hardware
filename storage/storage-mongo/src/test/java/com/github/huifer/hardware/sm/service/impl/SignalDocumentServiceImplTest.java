package com.github.huifer.hardware.sm.service.impl;

import com.github.huifer.hardware.common.enums.ReduceTypeEnums;
import com.github.huifer.hardware.common.enums.DeviceTypeEnums;
import com.github.huifer.hardware.sc.entity.SignQueryResponse;
import com.github.huifer.hardware.sc.entity.SignalDocument;
import com.github.huifer.hardware.sc.entity.SignalQuery;
import com.github.huifer.hardware.sc.service.SignalDocumentService;
import com.github.huifer.hardware.sm.CustomerBeans;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = {CustomerBeans.class})
@ActiveProfiles(value = {"mongo", "logging"})
class SignalDocumentServiceImplTest {


  Random random = new Random();
  @Autowired
  private MongoTemplate mongoTemplate;



  private Integer random(int max , int min){
    Random rand = new Random();
    return rand.nextInt(max - min + 1) + min;
  }

  @Test
  public void save() {
    List<String> deviceId = new ArrayList<>();
    deviceId.add("device_1");
    deviceId.add("device_2");
    deviceId.add("device_3");
    deviceId.add("device_4");

    List<String> deviceType = new ArrayList<>();
    for (DeviceTypeEnums value : DeviceTypeEnums.values()) {
      deviceType.add(value.getCode());
    }
    List<String> sig = new ArrayList<>();
    sig.add("sig_1");
    sig.add("sig_2");
    sig.add("sig_3");
    sig.add("sig_4");
    sig.add("sig_5");

    List<SignalDocument> saveData = new ArrayList<>();
    for (int i = 0; i < 10000; i++) {
      SignalDocument objectToSave = new SignalDocument();
      objectToSave.setDeviceType(deviceType.get(random(deviceType.size()-1,0)));
      objectToSave.setDeviceId(deviceId.get(random(deviceId.size()-1,0)));
      objectToSave.setKey(sig.get(random(sig.size()-1,0)));
      objectToSave.setValue(String.valueOf(random(10, 3)));
      ZonedDateTime zdt = ZonedDateTime.of(LocalDateTime.now(), ZoneId.systemDefault());

      objectToSave.setTime(zdt.toInstant().toEpochMilli());

      saveData.add(objectToSave);
    }
    signalDocumentService.saveAll(saveData);
  }

  @Test
  public void query(){
    SignalQuery signalQuery = new SignalQuery();
    signalQuery.setDeviceType(DeviceTypeEnums.YEWEI.getCode());
    signalQuery.setDeviceId("device_2");
    ArrayList<String> keys = new ArrayList<>();
    keys.add("sig_2");
    keys.add("sig_3");
    signalQuery.setKeys(keys);
    signalQuery.setStartTime(1678693692973L);
    signalQuery.setEndTime(167869369299L);

    signalDocumentService.query(signalQuery);
  }

  public static void main(String[] args) {
    System.out.println(1678693692974L);
    System.out.println(1678693692974L + 10 * 1000L);
  }

  @Test
  public void queryRange(){
    SignalQuery signalQuery = new SignalQuery();
    signalQuery.setDeviceType(DeviceTypeEnums.YEWEI.getCode());
    signalQuery.setDeviceId("device_2");
    ArrayList<String> keys = new ArrayList<>();
    keys.add("sig_3");
    keys.add("sig_1");
    keys.add("sig_2");
    signalQuery.setKeys(keys);
    signalQuery.setStartTime(1678693692974L);
    signalQuery.setEndTime(1678693692974L + 10 * 1000L);
    Map<String, Map<Integer, SignQueryResponse>> stringMapMap = signalDocumentService.querySingWithRange(
        signalQuery, ReduceTypeEnums.AVG, 1);
    System.out.println();
  }

  private SignalDocumentService signalDocumentService;

  @BeforeEach
  public void beforeEach() {
    signalDocumentService = new SignalDocumentServiceMongoImpl(mongoTemplate);
  }
}