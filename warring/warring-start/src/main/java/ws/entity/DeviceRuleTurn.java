package ws.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@EqualsAndHashCode
@ToString
public class DeviceRuleTurn {

  /**
   * 规则id
   **/
  private String ruleId;
  /**
   * 针对规则中主信号的开始时间
   **/
  private long startTime;
  /**
   * 针对规则中主信号的结束时间
   */
  private long endTime;

  /**
   * 轮次编号
   */
  private long turn;


}
