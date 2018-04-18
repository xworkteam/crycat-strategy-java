package com.xteam.crycat.strategy;

import com.alibaba.fastjson.JSONObject;
import com.xteam.crycat.base.RespEnums;
import com.xteam.crycat.base.StatusEnums;
import com.xteam.crycat.compiler.StrategyCompiler;
import com.xteam.crycat.thrift.Response;
import com.xteam.crycat.utils.FileUtils;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.xteam.crycat.base.Constants.*;

/**
 * @description 策略代码执行引擎
 * @package com.xteam.crycat.strategy
 * @author alyenc
 * @email alyenc@outlook.com
 * @date 2018/2/23 上午10:39
 * @version v1.2.0
 */
public class StratrgyEngineService extends AbstractStrategyService {
    //参数缓存
    private Map<String, String> initCache = new ConcurrentHashMap<>();
    //执行类缓存
    private Map<String, Class<?>> classCache = new ConcurrentHashMap<>();
    //策略实例状态缓存
    private Map<String, Integer> statusCache = new ConcurrentHashMap<>();
    //快照缓存
    private Map<String, String> snapShotCache = new ConcurrentHashMap<>();

    private Map<String, StrategyExecutor> executorCache = new ConcurrentHashMap<>();

    @Override
    protected Response doCreate(String params) {
        Response resp = new Response();

        //转换参数
        JSONObject json = JSONObject.parseObject(params);
        //策略实例ID，是执行中策略的唯一标识
        String id = json.getString("id");

        //检查当前策略是否存在
        String existStrategy = initCache.get(INIT_CACHE_PREFIX + id);
        if(existStrategy != null){
            resp.setCode(RespEnums.STRATEGY_INSTANCE_EXIST.getCode());
            resp.setMsg(RespEnums.STRATEGY_INSTANCE_EXIST.getMsg());
            resp.setSuccess(false);
            return resp;
        }

        //初始化策略类
        JSONObject strategy = json.getJSONObject("strategy");
        if(strategy == null){
            resp.setCode(RespEnums.STRATEGY_PARAMS_ERROR.getCode());
            resp.setMsg(RespEnums.STRATEGY_PARAMS_ERROR.getMsg());
            resp.setSuccess(false);
            return resp;
        }

        String code = strategy.getString("code");    //策略代码
        if(code == null) {
            resp.setCode(RespEnums.PARAMS_STRATEGY_CODE.getCode());
            resp.setMsg(RespEnums.PARAMS_STRATEGY_CODE.getMsg());
            resp.setSuccess(false);
            return resp;
        }

        StrategyCompiler compiler = new StrategyCompiler(code);
        compiler.compile();

        //缓存执行类
        classCache.putIfAbsent(CLASS_CACHE_PREFIX + id, compiler.getClazz());

        //缓存执行者
        StrategyExecutor exector = new StrategyExecutor(id, compiler.getClazz());
        executorCache.putIfAbsent(EXECUTOR_CACHE_PREFIX + id, exector);

        //将参数放入缓存，初始化参数，并持久化参数
        initCache.putIfAbsent(INIT_CACHE_PREFIX + id, params);
        FileUtils.writeText("conf" + File.separator + INIT_CACHE_PREFIX + id, params, false);

        //保存状态
        saveStatus(id, StatusEnums.CREATED.getCode());

        resp.setCode(RespEnums.OK.getCode());
        resp.setMsg(RespEnums.OK.getMsg());
        resp.setData(id);
        resp.setSuccess(true);
        System.out.println(resp);
        return resp;
    }

    @Override
    protected Response doStart(String params) {
        Response resp = new Response();

        //转换参数
        JSONObject json = JSONObject.parseObject(params);
        //策略实例ID，是执行中策略的唯一标识
        String id = json.getString("id");

        StrategyExecutor executor = executorCache.get(EXECUTOR_CACHE_PREFIX + id);
        executor.start();

        //保存状态
        saveStatus(id, StatusEnums.RUNNING.getCode());

        resp.setMsg(RespEnums.OK.getMsg());
        resp.setCode(RespEnums.OK.getCode());
        resp.setSuccess(true);
        return resp;
    }

    @Override
    protected Response doStop(String params) {
        Response resp = new Response();

        //转换参数
        JSONObject json = JSONObject.parseObject(params);
        //策略实例ID，是执行中策略的唯一标识
        String id = json.getString("id");

        StrategyExecutor executor = executorCache.get(EXECUTOR_CACHE_PREFIX + id);
        executor.stop();

        //清理缓存
        initCache.remove(INIT_CACHE_PREFIX + id);
        classCache.remove(CLASS_CACHE_PREFIX + id);
        statusCache.remove(STATUS_CACHE_PREFIX + id);
        executorCache.remove(EXECUTOR_CACHE_PREFIX + id);

        //清空配置文件
        FileUtils.delete("conf" + File.separator + STATUS_CACHE_PREFIX + id);
        FileUtils.delete("conf" + File.separator + INIT_CACHE_PREFIX + id);
        FileUtils.delete("conf" + File.separator + SNAPSHOT_CACHE_PREFIX + id);

        resp.setMsg(RespEnums.OK.getMsg());
        resp.setCode(RespEnums.OK.getCode());
        resp.setSuccess(true);
        return resp;
    }

    @Override
    protected Response doSuspend(String params) {
        Response resp = new Response();

        //转换参数
        JSONObject json = JSONObject.parseObject(params);
        //策略实例ID，是执行中策略的唯一标识
        String id = json.getString("id");

        StrategyExecutor executor = executorCache.get(EXECUTOR_CACHE_PREFIX + id);
        executor.suspend();

        //保存状态
        saveStatus(id, StatusEnums.PAUSE.getCode());

        resp.setMsg(RespEnums.OK.getMsg());
        resp.setCode(RespEnums.OK.getCode());
        resp.setSuccess(true);
        return resp;
    }

    @Override
    protected Response doResume(String params) {
        Response resp = new Response();

        //转换参数
        JSONObject json = JSONObject.parseObject(params);
        //策略实例ID，是执行中策略的唯一标识
        String id = json.getString("id");

        StrategyExecutor executor = executorCache.get(EXECUTOR_CACHE_PREFIX + id);
        if(executor == null){
            resp.setMsg(RespEnums.OK.getMsg());
            resp.setCode(RespEnums.OK.getCode());
            resp.setSuccess(true);
            return resp;
        }
        executor.resume();

        //保存状态
        saveStatus(id, StatusEnums.RUNNING.getCode());

        resp.setMsg(RespEnums.OK.getMsg());
        resp.setCode(RespEnums.OK.getCode());
        resp.setSuccess(true);
        return resp;

    }

    @Override
    protected Response doSnapshot(String params) {
        Response resp = new Response();

        return resp;
    }

    @Override
    protected Response doResumeFromSnap(String params) {
        Response resp = new Response();

        return resp;
    }

    private void saveStatus(String id, Integer status){
        //保存状态
        statusCache.putIfAbsent(STATUS_CACHE_PREFIX + id, status);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("status", status);
        FileUtils.writeText("conf" + File.separator + STATUS_CACHE_PREFIX + id, jsonObject.toJSONString(), false);
    }
}
