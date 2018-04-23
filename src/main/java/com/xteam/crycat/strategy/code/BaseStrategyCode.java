package com.xteam.crycat.strategy.code;

import com.xteam.crycat.bean.Exchange;

public interface BaseStrategyCode {


    void init(Exchange[] exchanges);

    void execute();
}
