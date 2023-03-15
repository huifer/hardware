package com.github.huifer.ss;

import com.github.huifer.hardware.sc.entity.SignalDocument;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;
import java.math.BigDecimal;
import java.util.Random;

public class Main {

  public static String token = "zN7E2EY7jXj58_yDfy1v2jtWT32IpCOFgfvoZdxYux7rctEX1NL-CjMZPy-6bvUQqNux_JigmGDMgoyN4_pcGg==";

  public static void main(String[] args) throws InterruptedException {
    Random random = new Random();

    InfluxDBClient influxDBClient = InfluxDBClientFactory.create("http://localhost:8086",
        token.toCharArray(),
        "admin", "my-bucket");
    WriteApiBlocking writeApi = influxDBClient.getWriteApiBlocking();

    for (int i = 0; i < 15; i++) {
      SignalDocument signalDocument = new SignalDocument();
      signalDocument.setDeviceType("类型1");
      if (i % 2 == 0) {
        signalDocument.setDeviceId("d_1");
        signalDocument.setA(BigDecimal.ONE);
      }else {
        signalDocument.setDeviceId("d_2");
        signalDocument.setA(BigDecimal.valueOf(1));
        signalDocument.setB(BigDecimal.valueOf(1));

      }
      writeApi.writeMeasurement(WritePrecision.MS, signalDocument);
//      Point point = Point.measurement("signal_document")
//          .addTag("device_type", "水质");
//      if (random.nextBoolean()) {
//        point.addTag("device_id", "d_1");
//        point.addField("a", BigDecimal.valueOf(random.nextInt(1)));
//
//      } else {
//        point.addTag("device_id", "d_1");
//        point.addField("a", BigDecimal.valueOf(random.nextInt(1)));
//      }
//      point.time(Instant.now().toEpochMilli(), WritePrecision.MS);
//      writeApi.writePoint( point );
      Thread.sleep(30);


    }
  }


}
