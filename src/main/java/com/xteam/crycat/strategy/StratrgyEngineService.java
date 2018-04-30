package com.xteam.crycat.strategy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.xteam.crycat.base.RespEnums;
import com.xteam.crycat.base.StatusEnums;
import com.xteam.crycat.base.StrategyEnums;
import com.xteam.crycat.bean.Exchange;
import com.xteam.crycat.compiler.StrategyCompiler;
import com.xteam.crycat.strategy.code.BaseStrategyCode;
import com.xteam.crycat.strategy.code.StrategyBridge;
import com.xteam.crycat.thrift.Response;
import com.xteam.crycat.utils.FileUtils;
import javassist.*;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static com.xteam.crycat.utils.Constants.*;

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
    private Map<String, BaseStrategyCode> objectCache = new ConcurrentHashMap<>();
    //策略实例状态缓存
    private Map<String, Integer> statusCache = new ConcurrentHashMap<>();
    //快照缓存
    private Map<String, String> snapShotCache = new ConcurrentHashMap<>();
    //策略执行体缓存
    private Map<String, StrategyExecutor> executorCache = new ConcurrentHashMap<>();

    @Override
    protected Response doCreate(String params) {
        Response resp = new Response();
        System.out.println(params);
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

        //获取交易所配置
        String exchStr = json.getString("exchanges");
        if(exchStr == null){
            resp.setCode(RespEnums.EXCHANGES_CONFIG_ERROR.getCode());
            resp.setMsg(RespEnums.EXCHANGES_CONFIG_ERROR.getMsg());
            resp.setSuccess(false);
            return resp;
        }

        //获取策略配置
        String strategy = json.getString("strategy");
        if(strategy == null){
            resp.setCode(RespEnums.STRATEGY_CONFIG_ERROR.getCode());
            resp.setMsg(RespEnums.STRATEGY_CONFIG_ERROR.getMsg());
            resp.setSuccess(false);
            return resp;
        }
        //创建策略类
        try {
            Class<?> straClass = createStrategyClass(strategy);   //创建策略类
            if(straClass == null){
                resp.setCode(RespEnums.STRATEGY_CONFIG_ERROR.getCode());
                resp.setMsg(RespEnums.STRATEGY_CONFIG_ERROR.getMsg());
                resp.setSuccess(false);
                return resp;
            }
            Exchange[] exchanges = initExchanges(exchStr);
            BaseStrategyCode baseStra = (BaseStrategyCode) straClass.newInstance();
            baseStra.init(exchanges);
            StrategyExecutor executor = new StrategyExecutor(id, baseStra);
            executorCache.putIfAbsent(EXECUTOR_CACHE_PREFIX + id, executor);
        } catch(Exception e){
            e.printStackTrace();
        }

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
        objectCache.remove(CLASS_CACHE_PREFIX + id);
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

    /**
     * @description 生成工具类，作为桥，调用策略类
     * @author alyenc
     * @date 2018/4/20 下午3:41
     * @param
     * @return
     */
    public Class<?> createStrategyClass(String strategy){
        JSONObject straJSON = JSON.parseObject(strategy);
        String code = straJSON.getString("code");
        if(code == null){
           return null;
        }
        JSONArray straParams = straJSON.getJSONArray("params");
        if(straParams == null){
            return null;
        }
        StringBuilder straParamsStr = new StringBuilder();
        straParams.forEach(item -> {
            JSONObject itemJSON = (JSONObject) (item);
            String desc = itemJSON.getString("desc");
            String key = itemJSON.getString("key");
            String type = itemJSON.getString("type");
            String value = itemJSON.getString("value");
            straParamsStr.append("private static ")
                    .append(type).append(" ").append(key).append(" = ").append(value)
                    .append(";").append("    ").append("//").append(desc).append("\n");

        });

        StringBuilder newCode = new StringBuilder();
        newCode.append("import com.xteam.crycat.bean.Exchange;\n");
        newCode.append("import com.xteam.crycat.strategy.code.BaseStrategyCode;\n");
        newCode.append("public class StrategyBridge implements BaseStrategyCode{ \n");

        newCode.append(straParamsStr);
        newCode.append("    private Exchange[] exchanges = null;\n");

        newCode.append("    @Override\n");
        newCode.append("    public void init(Exchange[] exchanges) { \n");
        newCode.append("        this.exchanges = exchanges;\n");
        newCode.append("    } \n");

        newCode.append("    @Override\n");
        newCode.append("    public void execute() { \n");
        newCode.append("        new StrategyCode().main();\n");
        newCode.append("    } \n");
        newCode.append(code);
        newCode.append("} \n");
        System.out.println(newCode);

        StrategyCompiler compiler = new StrategyCompiler(newCode.toString());
        compiler.compile();
        return compiler.getClazz();
    }

    private Exchange[] initExchanges(String exchParams){
        JSONArray exchJSON = JSON.parseArray(exchParams);
        List<Exchange> exchanges = new ArrayList<>();
        exchJSON.forEach(item -> {
            Map<String, Object> params = JSONObject.parseObject(JSONObject.toJSON(item).toString(), new TypeReference<Map<String, Object>>(){});
            Exchange exchange = new Exchange(params);
            exchange.init();
            exchanges.add(exchange);
        });

        return exchanges.toArray(new Exchange[0]);
    }

    public static void main(String[] args) {
        //redirectSystemOut();
        String str = "[{\"name\":\"gate\", \"apiKey\":\"\",\"apiSecret\":\"\",\"symbol\":\"theta_usdt\"}]";
        Exchange[] exchanges = new StratrgyEngineService().initExchanges(str);
        BaseStrategyCode baseStra = new StrategyBridge();
        baseStra.init(exchanges);
        baseStra.execute();
//        new Thread(() -> {
//            while (true) {
//                synchronized (baseStra) {
//                    try {
//                        baseStra.execute();
//                        Thread.sleep(30000);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();
    }

    /**
     *将System.out重定向到文件
     *
     */
    public static void redirectSystemOut() {
        try {
            System.setOut(new PrintStream(new FileOutputStream("/Users/mikechen/system.log")));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return;
        }
        System.out.println("This won't get displayed on the console, but sent to the file system_out.txt");
    }
}
