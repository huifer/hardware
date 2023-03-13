package com.github.huifer.hardware.sm;

import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TimeCalcGroup {


  public static void main(String[] args) {
    List<LocalTime> list = Arrays.asList(LocalTime.of(0,9),LocalTime.of(0,11),LocalTime.of(0,19));

    Map<Integer, List<LocalTime>> collect = list.stream()
        .collect(Collectors.
            groupingBy(x -> x.get(ChronoField.MINUTE_OF_DAY) / 10)
        );
    System.out.println();
  }

}
