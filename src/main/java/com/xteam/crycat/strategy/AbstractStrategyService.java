package com.xteam.crycat.strategy;

import com.xteam.crycat.thrift.Response;

import java.util.Map;

public abstract class AbstractStrategyService implements StrategyService {

    @Override
    public Response create(String params) {
        return doCreate(params);
    }

    @Override
    public Response start(String params) {
        return doStart(params);
    }

    @Override
    public Response stop(String params) {
        return doStop(params);
    }

    @Override
    public Response suspend(String params) {
        return doSuspend(params);
    }

    @Override
    public Response resume(String params) {
        return doResume(params);
    }

    @Override
    public Response snapshot(String params) {
        return doSnapshot(params);
    }

    @Override
    public Response resumeFromSnap(String params) {
        return doResumeFromSnap(params);
    }

    protected abstract Response doResumeFromSnap(String params);

    protected abstract Response doCreate(String params);

    protected abstract Response doStart(String params);

    protected abstract Response doStop(String params);

    protected abstract Response doSuspend(String params);

    protected abstract Response doResume(String params);

    protected abstract Response doSnapshot(String params);
}
