package com.github.huifer.hardware.sche.entity;

import com.github.huifer.hardware.sche.entity.RatingEntity.BigRange;
import com.github.huifer.hardware.sche.entity.TaskResult.CalcResult;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@EqualsAndHashCode
@ToString
public class VisualizationResultEntity {

  @Id
  private String id;

  private String visualizationFactorEntityId;


  /**
   * 原始公式运算结果，
   **/
  private List<CalcResult> data;
  /**
   * 评分明细（对原始数据）
   **/
  private List<RatingResult> dataRating;
  /**
   * 配合系数运算过后的数据
   **/
  private List<CalcResult> kData;
  /**
   * 评分明细（对原始数据,带系数）
   **/
  private List<RatingResult> kDataRating;

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @FieldNameConstants
  @EqualsAndHashCode
  @ToString
  public static class RatingResult {

    /**
     * {@link RatingEntity#id}
     */
    private String ratingId;
    /**
     * 评分规则名称 {@link RatingEntity#name}
     **/
    private String ratingName;
    /**
     * 最终评分名称 {@link BigRange#name}
     **/
    private String ratingResultName;
  }

}
