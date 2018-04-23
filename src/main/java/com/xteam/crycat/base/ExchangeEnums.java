package com.xteam.crycat.base;

import com.xteam.crycat.exchange.ExchangeService;
import com.xteam.crycat.exchange.GateExchangeService;
import com.xteam.crycat.exchange.ZbExchangeService;

import java.util.Arrays;

public enum ExchangeEnums {

    ZB ("zb"){
        @Override
        public ExchangeService getService() {
            return new ZbExchangeService();
        }
    },
    GATE("gate"){
        @Override
        public ExchangeService getService() {
            return new GateExchangeService();
        }
    };


    private String name;

    ExchangeEnums(String name){
        this.name = name;
    }

    public abstract ExchangeService getService();

    public static ExchangeEnums getEnumsByName(String name){
        return Arrays.stream(ExchangeEnums.values())
                .filter(item -> item.name.equals(name))
                .findFirst().get();
    }
}
