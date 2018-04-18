package com.xteam.crycat;

import com.xteam.crycat.base.StrategyEnums;
import com.xteam.crycat.strategy.StrategyService;

public class Test {

    public static void main(String[] args) {
        StrategyService s1 = StrategyEnums.INSTANCE.getInstance();
        StrategyService s2 = StrategyEnums.INSTANCE.getInstance();

        System.out.println(s1 == s2);
    }
}
