package com.xteam.crycat.strategy;

import com.xteam.crycat.exception.CryCatStrategyException;
import com.xteam.crycat.thrift.RobotExchange;
import com.xteam.crycat.thrift.RobotStrategy;
import com.xteam.crycat.utils.LogUtils;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.List;

/**
 * @description 策略代码执行引擎，JS
 * @package com.xteam.crycat.strategy
 * @author alyenc
 * @email alyenc@outlook.com
 * @date 2018/2/23 上午10:39
 * @version v1.2.0
 */
public class ScriptStratrgyService extends AbstractStrategyService {

    private String code = "";
    private List<RobotExchange> exchanges = null;
    private RobotStrategy strategy = null;   //策略参数
    /**
     * @description 初始化JS策略服务
     * @author alyenc
     * @date 2018/3/8 下午1:05
     */
    @Override
    public void doInitEngine(List<RobotExchange> exchanges, RobotStrategy strategy) {
        System.out.println("交易所：" + exchanges);
        this.exchanges = exchanges;
        this.strategy = strategy;
        this.code = strategy.getCode();
    }

    private String execute() {
        String script = createScriptCode(code);   //创建JS代码
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");
        try {
            engine.eval(script);
            if (engine instanceof Invocable) {
                Invocable invoke = (Invocable) engine;
                return (String)invoke.invokeFunction("execute",  this.exchanges, this.strategy);// 调用对应方法，并传入参数
            }
        }catch (NoSuchMethodException e) {
            LogUtils.error("no such js method error");
            throw new CryCatStrategyException();
        }catch(ScriptException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @description 生成完整js代码
     * @author alyenc
     * @date 2018/3/8 下午1:40
     * @param scriptCode
     * @return String
     */
    private static String createScriptCode(String scriptCode) {
        String body =
            "var LogUtils = Java.type(\"com.xteam.crycat.utils.LogUtils\");\n" +
                    "var UUID = Java.type(\"com.xteam.crycat.utils.UUIDUtils\");\n" +
                    "var Thread = Java.type(\"java.lang.Thread\");\n" +
                    "var threadExtender = Java.extend(Thread);\n" +
                    "var params = [];\n" +
                    "var exchanges = [];\n" +
                    "var tid = \"\";\n" +
                    "var thread = null;\n" +
                    "function createExchange(exchange) {\n" +
                    "    var obj = {\n" +
                    "        //sellFeeRate: exchange.getParams().sellFeeRate,\n" +
                    "        //buyFeeRate: exchange.getParams().buyFeeRate,\n" +
                    "        //transFeeRate: exchange.getParams().transFeeRate,\n" +
                    "        sellFeeRate: 0.02,\n" +
                    "        buyFeeRate: 0.02,\n" +
                    "        transFeeRate: 0.01,\n" +
                    "        getDepth: function(){\n" +
                    "           var temp = exchange.getDepth();\n" +
                    "           return eval(\"(\" + temp + \")\");\n" +
                    "        },\n" +
                    "        getTrades: function(){\n" +
                    "           return exchange.getTrades();\n" +
                    "        },\n" +
                    "        getTicker: function(){\n" +
                    "            var tickers = exchange.getTicker();\n" +
                    "            return eval(\"(\" + tickers + \")\");\n" +
                    "        }\n" +
                    "    }\n" +
                    "    return obj;\n" +
                    "}\n" +
                    "function Log(format, args) {\n" +
                    "    LogUtils.info(format, args);\n" +
                    "}\n" +
                    "function Sleep(millis) {\n" +
                    "    thread.sleep(millis);\n" +
                    "}\n" +
                    "function execute(exchanges, strategy) {\n" +
                    "    for(var i in exchanges) {\n" +
                    "        var exClass = Java.type(exchanges[i].getClazz());\n" +
                    "        var exObject = new exClass();\n" +
                    "        var exparams = exchanges[i].getParams();\n" +
                    "        var symbols = exchanges[i].getSymbols();\n" +
                    "        exObject.init(exparams, symbols);   //初始化交易所\n" +
                    "        this.exchanges.push(createExchange(exObject));\n" +
                    "    }\n" +
                    "    params = strategy.getParams();\n" +
                    "    tid = UUID.getUUID();\n" +
                    "    thread = new threadExtender(tid){\n" +
                    "         run: function() { \n" +
                    "              main();\n" +
                    "              print(\"Thread running!\");\n" +
                    "         }\n" +
                    "    };\n" +
                    "    thread.start();\n" +
                    "    return tid;\n" +
                    "}\n" +
                    "function main(){\n" +
                    "    ticker();\n" +
                    "};\n" +
                    "\n" +
                    "function ticker(){\n" +
                    "    if(exchanges.leght < 2) {\n" +
                    "\n" +
                    "    }\n" +
                    "    print(JSON.stringify(exchanges));\n" +
                    "    var depthA = exchanges[0].getDepth();\n" +
                    "    var depthB = exchanges[1].getDepth();\n" +
                    "\n" +
                    "    var asksOne = depthA[0].asks[0].price;\n" +
                    "    var bidsOne = depthB[0].bids[0].price;\n" +
                    "\n" +
                    "    var bidsFive = depthA[0].bids[4].price;\n" +
                    "    var asksFive = depthB[0].asks[4].price;\n" +
                    "\n" +
                    "    var tickers = exchanges[1].getTicker();\n" +
                    "    var curPrice = tickers[0].last;\n" +
                    "\n" +
                    "    var realSellOnePrice = realSellPrice(asksOne, exchanges[0].sellFeeRate);\n" +
                    "    var realBuyOnePrice = realBuyPrice(bidsOne, exchanges[1].buyFeeRate);\n" +
                    "    var realSellFivePrice = realSellPrice(asksFive, exchanges[0].sellFeeRate);\n" +
                    "    var realBuyFivePrice = realBuyPrice(bidsFive, exchanges[1].buyFeeRate);\n" +
                    "    var realTransFee = transFee(curPrice, exchanges[1].transFeeRate);\n" +
                    "\n" +
                    "    var checkDiff = checkDiff(realSellOnePrice, realBuyOnePrice, realSellFivePrice, realBuyFivePrice, realTransFee, diff);\n" +
                    "        \n" +
                    "    if(checkDiff) {\n" +
                    "        print(true);\n" +
                    "    }\n" +
                    "}\n" +
                    "\n" +
                    "function checkDiff(sellOnePrice, buyOnePrice, sellFivePrice, buyFivePrice, transFee, DefaultDiff) {\n" +
                    "    var b1 = new Number(buyOnePrice);\n" +
                    "    var s1 = new Number(sellOnePrice);\n" +
                    "    var s5 = new Number(sellFivePrice);\n" +
                    "    var b5 = new Number(buyFivePrice);\n" +
                    "\n" +
                    "    var t = new Number(transFee);\n" +
                    "    var d = new Number(DefaultDiff);\n" +
                    "\n" +
                    "    var diff = s1 - b1 - t;\n" +
                    "\n" +
                    "    return diff > 0;\n" +
                    "}\n" +
                    "\n" +
                    "function realSellPrice(sellPrice, sellFeeRate) {\n" +
                    "    var s = new Number(sellPrice);\n" +
                    "    var r =new Number(sellFeeRate);\n" +
                    "    return s.valueOf() - r.valueOf() * s.valueOf();\n" +
                    "}\n" +
                    "\n" +
                    "function realSellPrice(buyPrice, buyFeeRate) {\n" +
                    "    var b = new Number(buyPrice);\n" +
                    "    var r =new Number(buyFeeRate);\n" +
                    "    return b.valueOf() + r.valueOf() * b.valueOf();\n" +
                    "}\n" +
                    "\n" +
                    "function transFee(curPrice, transFee) {\n" +
                    "    var p = new Number(curPrice);\n" +
                    "    var r =new Number(transFee);\n" +
                    "    return p.valueOf() * r.valueOf();\n" +
                    "}";
        System.out.println(body + scriptCode);
        return body + scriptCode;
    }

    @Override
    protected void doExit() {
    }

    @Override
    protected String doStart() {
        String tid = execute();
        System.out.println(tid);
        return tid;
    }
}
