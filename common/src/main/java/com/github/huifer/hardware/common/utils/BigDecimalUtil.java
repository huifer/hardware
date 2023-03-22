package com.github.huifer.hardware.common.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BigDecimalUtil {


  public static boolean between(BigDecimal source, BigDecimal max, BigDecimal min) {
    return (source.compareTo(max) < 1) &&
        (source.compareTo(min) > -1);
  }


  public static boolean betweenList(BigDecimal source, BigDecimal[][] range) {

    List<Boolean> booleans = new ArrayList<>();
    for (BigDecimal[] bigDecimals : range) {
      if (bigDecimals.length == 2) {
        BigDecimal min = bigDecimals[0];
        BigDecimal max = bigDecimals[1];
        boolean between = between(source, max, min);
        booleans.add(between);
      }
    }
    return booleans.contains(true);
  }

  public static boolean betweenList(BigDecimal source, List<List<BigDecimal>> range) {

    List<Boolean> booleans = new ArrayList<>();
    for (List<BigDecimal> bigDecimals : range) {
      if (bigDecimals.size() == 2) {
        BigDecimal min = bigDecimals.get(0);
        BigDecimal max = bigDecimals.get(1);
        boolean between = between(source, max, min);
        booleans.add(between);
      }
    }
    return booleans.contains(true);
  }
}
