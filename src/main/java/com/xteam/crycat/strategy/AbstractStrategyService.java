package com.xteam.crycat.strategy;

import com.xteam.crycat.thrift.RobotExchange;
import com.xteam.crycat.thrift.RobotStrategy;

import java.util.List;

public abstract class AbstractStrategyService implements StrategyService {

    @Override
    public void initEngine(List<RobotExchange> exchanges, RobotStrategy strategy) {
        doInitEngine(exchanges, strategy);
    }

    public String start() {
        return doStart();
    }

    public void exit() {
        doExit();
    }

    protected abstract void doInitEngine(List<RobotExchange> exchanges, RobotStrategy strategy);

    protected abstract String doStart();

    protected abstract void doExit();

}
