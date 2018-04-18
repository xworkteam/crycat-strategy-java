package com.xteam.crycat.strategy;

import com.xteam.crycat.thrift.Response;

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

    Response create(String params);

    Response start(String params);

    Response stop(String params);

    Response snapshot(String params);

    Response suspend(String params);

    Response resume(String params);

    Response resumeFromSnap(String params);
}
