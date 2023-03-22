package com.github.huifer.hardware.sche.inf;

import com.github.huifer.hardware.sche.entity.FilterEntity;
import com.github.huifer.hardware.sche.entity.dto.QueryResponse;
import java.math.BigDecimal;
import java.util.Iterator;

public interface DataExtractService {

  /**
   * 提取数据
   *
   * @param query 查询对象
   * @return key: 信号标识,value: 查询到的信号值
   **/
  QueryResponse extract(com.github.huifer.hardware.sche.entity.QueryEntity query);

  /**
   * 过滤数据
   *
   * @param data   数据
   * @param filter 过滤对象
   * @return key: 信号标识，value: 过滤后需要进行的数据
   **/
  default QueryResponse filter(QueryResponse data, FilterEntity filter) {
    if (filter != null) {
      for (Iterator<BigDecimal> iterator = data.getData().iterator(); iterator.hasNext(); ) {
        BigDecimal datum = iterator.next();
        if (filter.ignore(datum)) {
          iterator.remove();
        }
      }
      return data;
    }
    return data;
  }
}
