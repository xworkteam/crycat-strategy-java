package com.xteam.crycat.remote;

import com.xteam.crycat.thrift.RemoteService;
import com.xteam.crycat.thrift.Request;
import com.xteam.crycat.thrift.Response;

import java.util.Map;

public class RemoteServiceImpl implements RemoteService.Iface{

    @Override
    public Response invoke(Request request) {
        String method = request.method;
        Map<String, String> params = request.params;
        return null;
    }
}
