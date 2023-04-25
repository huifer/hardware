package ws.service.impl;

import com.github.huifer.hd.ws.entity.DeviceRuleTurn;
import com.github.huifer.hd.ws.service.DeviceWarringRuleService;
import com.google.gson.Gson;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

public class DeviceWarringRuleServiceImpl implements DeviceWarringRuleService {


  public static final String RULE_TURN_KEY = "device_rule_turn";
  Gson gson = new Gson();
  @Autowired
  private StringRedisTemplate stringRedisTemplate;

  /**
   * 添加轮次
   *
   * @param ruleId 规则id
   * @param time   数据上报时间
   * @return 轮次号
   */
  public long addTurn(String ruleId, long time) {
    String key = genTurnKey(ruleId);
    // 判断是否存在key
    if (stringRedisTemplate.hasKey(key)) {
      // 存在key
      Long size = stringRedisTemplate.opsForList().size(key);
      assert size != null;
      List<String> deviceRuleTurns = stringRedisTemplate.opsForList().rightPop(key, 1);
      return extracted(ruleId, time, key, deviceRuleTurns);

    } else {
      // 不存在key
      // 从右侧取出2个，
      List<String> deviceRuleTurns = stringRedisTemplate.opsForList().rightPop(key, 2);

      return extracted(ruleId, time, key, deviceRuleTurns);
    }

  }

  private long extracted(String ruleId, long time, String key, List<String> deviceRuleTurns) {
    DeviceRuleTurn d = null;

    assert deviceRuleTurns != null;
    for (String string : deviceRuleTurns) {
      DeviceRuleTurn deviceRuleTurn = gson.fromJson(string, DeviceRuleTurn.class);
      long endTime = deviceRuleTurn.getEndTime();
      long startTime = deviceRuleTurn.getStartTime();
      if (startTime <= time && time <= endTime) {
        d = deviceRuleTurn;
      }
    }

    if (d == null) {

      String s = deviceRuleTurns.get(deviceRuleTurns.size() - 1);
      DeviceRuleTurn last = gson.fromJson(s, DeviceRuleTurn.class);

      DeviceRuleTurn deviceRuleTurn = new DeviceRuleTurn();
      deviceRuleTurn.setRuleId(ruleId);
      deviceRuleTurn.setStartTime(time);
      // TODO: 2023/4/25 获取规则轮次时间
      deviceRuleTurn.setEndTime(0L);
      deviceRuleTurn.setTurn(last.getTurn() + 1L);
      d = deviceRuleTurn;
    }

    stringRedisTemplate.opsForList().rightPush(key, gson.toJson(d));

    return d.getTurn();
  }


  private String genTurnKey(String ruleId) {
    return RULE_TURN_KEY + ":" + ruleId;
  }


}
