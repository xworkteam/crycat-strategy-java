package com.xteam.crycat.remote;

import com.xteam.crycat.base.StrategyEnums;
import com.xteam.crycat.strategy.StrategyService;
import com.xteam.crycat.thrift.RemoteService;
import com.xteam.crycat.thrift.Request;
import com.xteam.crycat.thrift.Response;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RemoteServiceImpl implements RemoteService.Iface{

    @Override
    public Response invoke(Request request) {
        Response resp = new Response();
        String method = request.method;

        Method[] methodArray = StrategyService.class.getMethods();
        List<Method> methodList = Arrays.asList(methodArray);
        Map<String, Method> methods = methodList.stream()
                .collect(Collectors.toMap(Method::getName, item->item));

        Method existMethod = methods.get(method);
        if(existMethod == null){
            resp.setSuccess(false);
            resp.setCode("");
            resp.setMsg("");
            return resp;
        }

        String params = request.params;
        try {
            resp = (Response) existMethod.invoke(StrategyEnums.INSTANCE.getInstance(), params);
        }catch(Exception e){
           e.printStackTrace();
        }

        return resp;
    }

    public static void main(String[] args) {
//        RemoteServiceImpl remote = new RemoteServiceImpl();
//
//        Request request = new Request();
//        request.setMethod("create");
//        request.setParams("{\"id\":\"aaaaaa\",\"strategy\":\"\"}");
//        System.out.println(remote.invoke(request));

        StrategyService s1 = StrategyEnums.INSTANCE.getInstance();
        StrategyService s2 = StrategyEnums.INSTANCE.getInstance();

        System.out.println(s1 == s2);
    }
}
