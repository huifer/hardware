package com.github.huifer.hardware.sm.service.impl;

import com.github.huifer.hardware.common.enums.ReduceTypeEnums;
import com.github.huifer.hardware.common.enums.DeviceTypeEnums;
import com.github.huifer.hardware.sc.entity.SignQueryResponse;
import com.github.huifer.hardware.sc.entity.SignalDocument;
import com.github.huifer.hardware.sc.entity.SignalDocument.Fields;
import com.github.huifer.hardware.sc.entity.SignalQuery;
import com.github.huifer.hardware.sc.service.SignalDocumentService;
import com.mongodb.MongoException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.DocumentCallbackHandler;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

public class SignalDocumentServiceMongoImpl implements SignalDocumentService {

  public static final String SignalDocumentServiceMongoBeanName = "SignalDocumentServiceMongoBean";
  public static final String DEVICE_COLLECTION_PRE = "data_";
  private static final Logger logger = LoggerFactory.getLogger(
      SignalDocumentServiceMongoImpl.class);
  private final MongoTemplate mongoTemplate;

  public SignalDocumentServiceMongoImpl(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  @Transactional(rollbackFor = {Exception.class})

  public void save(SignalDocument signalDocument) {
    if (org.apache.commons.lang3.StringUtils.isEmpty(signalDocument.getDeviceType())) {
      throw new IllegalArgumentException("设备类型必填");
    }
    // TODO: 2023/4/11 设备类型转换模式处理
    DeviceTypeEnums conv = DeviceTypeEnums.conv(signalDocument.getDeviceType());
    if (conv == null) {
      throw new IllegalArgumentException("设备类型无法识别");
    }
    String deviceId = signalDocument.getDeviceId();
    if (StringUtils.isEmpty(deviceId)) {
      throw new IllegalArgumentException("设备id必填");
    }
    mongoTemplate.save(signalDocument, buildCollectionName(conv, deviceId));
  }

  private String buildCollectionName(DeviceTypeEnums enums, String deviceId) {
    return (DEVICE_COLLECTION_PRE + enums.getCode() + "_" + deviceId).toLowerCase(Locale.ROOT);
  }

  @Override
  public Map<String, List<SignalDocument>> query(SignalQuery signalQuery) {
    List<SignalDocument> res = getSignalDocuments(signalQuery);

    Map<String, List<SignalDocument>> collect = res.stream()
        .collect(Collectors.groupingBy((SignalDocument::getKey)));

    return collect;
  }

  @NotNull
  private List<SignalDocument> getSignalDocuments(SignalQuery signalQuery) {
    String deviceType = signalQuery.getDeviceType();

    if (StringUtils.isEmpty(deviceType)) {
      throw new IllegalArgumentException("设备类型必填");
    }
    // TODO: 2023/4/11 设备类型转换模式处理
    DeviceTypeEnums conv = DeviceTypeEnums.conv(deviceType);
    if (conv == null) {
      throw new IllegalArgumentException("设备类型无法识别");
    }
    String deviceId = signalQuery.getDeviceId();
    if (StringUtils.isEmpty(deviceId)) {
      throw new IllegalArgumentException("设备id必填");
    }

    List<Criteria> criteria = new ArrayList<>();
    if (!CollectionUtils.isEmpty(signalQuery.getKeys())) {
      criteria.add(Criteria.where(Fields.key).in(signalQuery.getKeys()));
    }
    Long startTime = signalQuery.getStartTime();
    Long endTime = signalQuery.getEndTime();
    if (startTime != null && endTime != null) {
      criteria.add(Criteria.where(Fields.time).gte(startTime).lte(endTime));
    }

    Query query = new Query();
    for (Criteria criterion : criteria) {
      query.addCriteria(criterion);
    }
    List<SignalDocument> res = new ArrayList<>();
    mongoTemplate.executeQuery(query, buildCollectionName(conv, signalQuery.getDeviceId()),
        new DocumentCallbackHandler() {
          @Override
          public void processDocument(Document document)
              throws MongoException, DataAccessException {
            ObjectId objectId = document.getObjectId("_id");
            SignalDocument e = new SignalDocument();
            e.setId(objectId.toString());
            e.setDeviceType(document.getString(Fields.deviceType));
            e.setDeviceId(document.getString(Fields.deviceId));
            e.setKey(document.getString(Fields.key));
            e.setValue(document.getString(Fields.value));
            e.setTime(document.getLong(Fields.time));

            res.add(e);
          }
        });
    return res;
  }

  @Override
  public Map<String, Map<Integer, SignQueryResponse>> querySingWithRange(
      SignalQuery signalQuery,
      ReduceTypeEnums reduceTypeEnums,
      int sec
  ) {

    if (signalQuery.getStartTime() == null) {
      throw new IllegalArgumentException("开始时间必填");
    }
    if (signalQuery.getEndTime() == null) {
      throw new IllegalArgumentException("结束时间必填");
    }
    List<String> keys = signalQuery.getKeys();
//    if (keys.size() > 1) {
//      throw new IllegalArgumentException("只能搜索一个信号");
//    }

    List<SignalDocument> queryList = getSignalDocuments(signalQuery);
    if (CollectionUtils.isEmpty(queryList)) {
      return null;
    }
    // 按照查询时间直接进行分片
    Long startTime = signalQuery.getStartTime();
    Long endTime = signalQuery.getEndTime();
    if (startTime != null && endTime != null) {
      List<LongRange> longRanges = timeRange(signalQuery.getStartTime(), signalQuery.getEndTime(),
          sec * 1000L);

      Map<String, Map<Integer, SignQueryResponse>> res = new HashMap<>();
      for (String key : keys) {
        Map<Integer, SignQueryResponse> data = new HashMap<>();

        for (int i = 0; i < longRanges.size(); i++) {
          LongRange longRange = longRanges.get(i);
          BigDecimal bigDecimal = selectForReduceType(reduceTypeEnums, queryList, longRange, key);

          SignQueryResponse value = new SignQueryResponse();
          value.setDeviceType(signalQuery.getDeviceType());
          value.setDeviceId(signalQuery.getDeviceId());
          value.setKey(key);
          value.setValue(bigDecimal.toString());
          value.setStart(longRange.start);
          value.setEnd(longRange.end);
          value.setTimeRange(longRange.start + "-" + longRange.end);

          data.put(i+1, value);
        }
        res.put(key, data);
      }

      return res;

    }

    return null;
  }

  private BigDecimal selectForReduceType(ReduceTypeEnums reduceTypeEnums,
      List<SignalDocument> queryList, LongRange longRange, String key) {
    List<BigDecimal> calcData = new ArrayList<>();

    for (SignalDocument signalDocument : queryList) {
      long time = signalDocument.getTime();
      if (longRange.end >= time && longRange.start <= time && signalDocument.getKey().equals(key)) {
        String value = signalDocument.getValue();
        try {
          calcData.add(new BigDecimal(value));
        } catch (Exception e) {
          calcData.add(BigDecimal.ZERO);
        }
      }
    }

    BigDecimal d = BigDecimal.ZERO;

    if (Objects.requireNonNull(reduceTypeEnums) == ReduceTypeEnums.AVG) {
      d = average(calcData, RoundingMode.UP);
    } else if (reduceTypeEnums == ReduceTypeEnums.MAX) {
      Optional<BigDecimal> max = calcData.stream()
          .max(BigDecimal::compareTo);

      if (max.isPresent()) {
        d = max.get();
      }
    } else if (reduceTypeEnums == ReduceTypeEnums.MIN) {
      Optional<BigDecimal> min = calcData.stream()
          .min(BigDecimal::compareTo);
      if (min.isPresent()) {
        d = min.get();
      }
    } else if (reduceTypeEnums == ReduceTypeEnums.SUM) {
      d = calcData.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    return d;
  }

  public BigDecimal average(List<BigDecimal> bigDecimals, RoundingMode roundingMode) {
    BigDecimal sum = bigDecimals.stream()
        .map(Objects::requireNonNull)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
    long count = bigDecimals.stream().filter(Objects::nonNull).count();
    try {
      BigDecimal divide = sum.divide(new BigDecimal(count), roundingMode);
      logger.info("求和结果= {} , 数量 = {} 商 = {}", sum, count, divide);
      return divide;
    } catch (Exception e) {
      return BigDecimal.ZERO;
    }
  }

  private List<LongRange> timeRange(long st, long et, long step) {
    List<LongRange> res = new ArrayList<>();
    long cu = st;

    while (true) {
      LongRange e = new LongRange();
      e.start = cu;
      cu = cu + step;
      e.end = cu;
      res.add(e);
      if (cu > et) {
        break;
      }

    }

    return res;
  }

  class LongRange {

    private long start;
    private long end;
  }


}
