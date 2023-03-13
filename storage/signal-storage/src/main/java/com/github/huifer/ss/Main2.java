package com.github.huifer.ss;

import cn.hutool.core.util.RandomUtil;
import com.influxdb.Cancellable;
import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;
import com.influxdb.client.BucketsApi;
import com.influxdb.client.DeleteApi;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.OrganizationsApi;
import com.influxdb.client.QueryApi;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.Bucket;
import com.influxdb.client.domain.BucketRetentionRules;
import com.influxdb.client.domain.DeletePredicateRequest;
import com.influxdb.client.domain.Organization;
import com.influxdb.client.domain.Query;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import com.influxdb.query.FluxRecord;
import com.influxdb.query.FluxTable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

public class Main2 {

  public static final String CREATE_TEST_BUCKET = "test-create";
  public static final String ORG_ID = "a4cf876466759b27";
  public static String token = "zN7E2EY7jXj58_yDfy1v2jtWT32IpCOFgfvoZdxYux7rctEX1NL-CjMZPy-6bvUQqNux_JigmGDMgoyN4_pcGg==";
  static Random random = new Random();

  public static void main(final String[] args) throws InterruptedException {
    // You can generate an API token from the "API Tokens Tab" in the UI
    String bucket = "my-bucket";
    String org = "admin";

    InfluxDBClient influxDBClient = InfluxDBClientFactory.create("http://localhost:8086",
        token.toCharArray());

    List<Organization> organizations = influxDBClient.getOrganizationsApi().findOrganizations();
    System.out.println();
    BucketRetentionRules retention = new BucketRetentionRules();
    retention.setEverySeconds(3600);
    OrganizationsApi organizationsApi = influxDBClient.getOrganizationsApi();

    BucketsApi bucketsApi = influxDBClient.getBucketsApi();

    Bucket bucketByName = bucketsApi.findBucketByName(CREATE_TEST_BUCKET);

    InfluxDBClient c2 = InfluxDBClientFactory.create("http://localhost:8086", token.toCharArray(),
        org, "my-bucket");
    WriteApiBlocking writeApi = c2.getWriteApiBlocking();
    for (int i = 0; i < 1000; i++) {
      Point point = Point.measurement("device_water_g")
          .addTag("device_type", "水质");

      boolean b = random.nextBoolean();

      if (b) {
        point.addTag("device_id", "d_1");

        point.addField("a", RandomUtil.randomInt(10, 30));
        point.addField("b", RandomUtil.randomInt(10, 30));
      }else {
        point.addTag("device_id", "d_2");
        point.addField("c", RandomUtil.randomInt(10, 30));
      }

      point.time(Instant.now().toEpochMilli(), WritePrecision.MS);

      writeApi.writePoint(point);
      Thread.sleep(30);

    }

    String flux = "from(bucket:\"my-bucket\")  |> range(start: 0)  ";

    QueryApi queryApi = c2.getQueryApi();
    List<DeviceDocu> query = queryApi.query(flux, DeviceDocu.class);

    System.out.println();
    c2.close();
    influxDBClient.close();

  }

  @Measurement(name = "device_water_g")
  private static class DeviceDocu {

    @Column(name = "device_type", tag = true)
    String deviceType;

    @Column(name = "device_id",tag = true)
    String deviceId;

    @Column(name = "a")
    String  a;
    @Column(name = "b")
    String  b;
    @Column(name = "c")
    String  c;


    @Column(timestamp = true)
    Instant time;
  }

}
