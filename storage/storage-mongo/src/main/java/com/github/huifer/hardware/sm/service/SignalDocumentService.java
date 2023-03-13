package com.github.huifer.hardware.sm.service;

import com.github.huifer.hardware.sc.influx.ReduceTypeEnums;
import com.github.huifer.hardware.sc.influx.SignQueryResponse;
import com.github.huifer.hardware.sc.influx.SignalDocument;
import com.github.huifer.hardware.sc.influx.SignalQuery;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface SignalDocumentService {

  void save(SignalDocument signalDocument);

  default void saveAll(Collection<SignalDocument> signalDocument) {
    signalDocument.forEach(this::save);
  }

  /**
   * @param signalQuery
   * @return key: 信号，value: 对象
   */
  Map<String, List<SignalDocument>> query(SignalQuery signalQuery);


  /**
   * 单个信号搜索
   * @param signalQuery
   * @param reduceTypeEnums 数据归集方式
   * @param sec 间隔多少秒
   * @return
   */
  Map<String  , Map<String, SignQueryResponse>> querySingWithRange(
      SignalQuery signalQuery,
      ReduceTypeEnums reduceTypeEnums,
      int sec
  );


}
