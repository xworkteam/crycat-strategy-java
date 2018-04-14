package com.xteam.crycat.strategy;

import java.util.List;
import java.util.Map;

public abstract class AbstractStrategyService implements StrategyService {

    @Override
    public void create(Map<String, String> params) {
        doCreate(params);
    }

    @Override
    public void start(Map<String, String> params) {
        doStart(params);
    }

    @Override
    public void stop(Map<String, String> params) {
        doStop(params);
    }

    @Override
    public void suspend(Map<String, String> params) {
        doSuspend(params);
    }

    @Override
    public void resume(Map<String, String> params) {
        doResume(params);
    }

    @Override
    public void snapshot(Map<String, String> params) {
        doSnapshot(params);
    }

    protected abstract void doCreate(Map<String, String> params);

    protected abstract void doStart(Map<String, String> params);

    protected abstract void doStop(Map<String, String> params);

    protected abstract void doSuspend(Map<String, String> params);

    protected abstract void doResume(Map<String, String> params);

    protected abstract void doSnapshot(Map<String, String> params);
}
