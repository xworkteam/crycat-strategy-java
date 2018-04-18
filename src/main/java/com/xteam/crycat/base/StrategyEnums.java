package com.xteam.crycat.base;

import com.xteam.crycat.strategy.StrategyService;
import com.xteam.crycat.strategy.StratrgyEngineService;

public enum StrategyEnums {
    INSTANCE;

    private StrategyService service = null;

    public StrategyService getInstance(){
        if(service == null){
            service = new StratrgyEngineService();
        }
        return service;
    }
}
