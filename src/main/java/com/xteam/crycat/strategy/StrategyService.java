package com.xteam.crycat.strategy;

import java.util.List;
import java.util.Map;

/**
 * @description 量化策略接口
 * @package com.xteam.crycat.strategy
 * @author alyenc
 * @email alyenc@outlook.com
 * @date 2018/1/31 下午3:25
 * @version v1.0.0
 */
public interface StrategyService {

    void create(Map<String, String> params);

    void start(Map<String, String> params);

    void stop(Map<String, String> params);

    void snapshot(Map<String, String> params);

    void suspend(Map<String, String> params);

    void resume(Map<String, String> params);
}
