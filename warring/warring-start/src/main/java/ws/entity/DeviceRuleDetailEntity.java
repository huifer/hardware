package ws.entity;

import java.math.BigDecimal;
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
public class DeviceRuleDetailEntity {

  /**
   * 比较符号 大于小于等 只有数学数字比较
   */
  private String operator;

  /**
   * 设备类型信号
   */
  private String deviceTypeSignalName;

  /**
   * 小值
   */
  private BigDecimal min;
  /**
   * 大值
   */
  private BigDecimal max;


}
