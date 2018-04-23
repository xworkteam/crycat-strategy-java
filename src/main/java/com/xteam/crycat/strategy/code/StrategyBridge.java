package com.xteam.crycat.strategy.code;

import com.xteam.crycat.bean.Exchange;
import com.xteam.crycat.strategy.code.BaseStrategyCode;

public class StrategyBridge implements BaseStrategyCode{

    private static Double diff = 0.12;    //价差
    private Exchange[] exchanges = null;
    @Override
    public void init(Exchange[] exchanges) {
        this.exchanges = exchanges;
    }
    @Override
    public void execute() {
        new StrategyCode().main();
    }
    class StrategyCode {
        public void main() {
            System.out.println(diff);
        }
    }
}

