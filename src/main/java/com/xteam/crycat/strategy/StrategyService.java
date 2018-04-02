package com.xteam.crycat.strategy;

import com.xteam.crycat.thrift.RobotExchange;
import com.xteam.crycat.thrift.RobotStrategy;

import java.util.List;

/**
 * @description 量化策略接口
 * @package com.xteam.crycat.strategy
 * @author alyenc
 * @email alyenc@outlook.com
 * @date 2018/1/31 下午3:25
 * @version v1.0.0
 */
public interface StrategyService {

    void initEngine(List<RobotExchange> exchanges, RobotStrategy strategy);
    /**
     * @description 开始执行策略-策略入口
     * @author alyenc
     * @date 2018/2/1 下午1:53
     */
    String start();

    void exit();
}
