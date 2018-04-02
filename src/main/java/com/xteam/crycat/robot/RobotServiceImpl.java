package com.xteam.crycat.robot;

import com.xteam.crycat.strategy.StrategyService;
import com.xteam.crycat.thrift.Response;
import com.xteam.crycat.thrift.RobotExchange;
import com.xteam.crycat.thrift.RobotService;
import com.xteam.crycat.thrift.RobotStrategy;
import com.xteam.crycat.utils.LogUtils;
import org.apache.thrift.TException;

import java.util.List;

public class RobotServiceImpl implements RobotService.Iface{

    private StrategyService engine = null;

    @Override
    public Response initRobot(List<RobotExchange> exchanges, RobotStrategy strategy) throws TException {
        try{
            engine = (StrategyService)Class.forName(strategy.getClazz()).newInstance();
        } catch(Exception e) {
            e.printStackTrace();
        }
        engine.initEngine(exchanges, strategy);
        Response res = new Response();
        res.setIsSuccess(true);
        res.setMsg("success");
        res.setData("");
        return res;
    }

    @Override
    public Response startRobot() throws TException {
        LogUtils.info("thrift 实现类已经执行,startRobot");
        engine.start();
        Response res = new Response();
        res.setIsSuccess(true);
        res.setMsg("success");
        res.setData("");
        return res;
    }
}
