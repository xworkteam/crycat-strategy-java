package com.xteam.crycat.utils;

public class Constants {
    /********************************Common START***************************/

    //Redis Keys
    public final static String REDIS_STRATEGY_PREFIX = "strategy_";    //用户配置策略前缀

    public final static String REDIS_EXCHANGE_PREFIX = "exchange_";

    public final static String REDIS_ROBOT_PREFIX = "robot_";

    public final static String REDIS_ALL_CONFIG_SUFFIX = "all";

    public final static String REDIS_SYMBOL_PREFIX = "symbol_";

    public final static String REDIS_BASE_SYMBOL_PREFIX = "symbol_base_";

    public final static String REDIS_KEY_PREFIX = "key_";

    public final static String REDIS_USER_PREFIX = "user_";

    /********************************Common END***************************/





    //默认值
    public final static String DEFAULT = "default_";

    //价差代码
    public final static String DIFFERENCE = "difference";

    //交易所代码
    public final static String HUOBI = "huobi_";
    public final static String BIAN = "bian_";
    public final static String GATE = "gate_";
    public final static String ZB = "zb_";
    public final static String OKEX = "okex_";


    public final static String EXCHANGE_ONE = "exchange_1_";
    public final static String EXCHANGE_TWO = "exchange_2_";

    //交易买方卖方代码
    public final static String BUY = "buy_";
    public final static String SELL = "sell_";

    //数量和价格代码
    public final static String PRICE = "_price";
    public final static String AMOUNT = "_amount";

    /********************************Gate START***************************/
    public final static String GATE_URL_PREFIX = "/api2/1/orderBook";

    //Gate市场深度接口地址
    public final static String GATE_DEPTH = "/api2/1/orderBook";

    //Gate.io API ****重要，不能泄漏****
    public final static String GATE_API_KEY = "";
    public final static String GATE_API_SECRET = "";

    /********************************Gate END***************************/


    /********************************HuoBi START***************************/
    public final static String HUOBI_URL_PREFIX = "https://api.huobi.pro";

    //火币市场深度接口地址
    public final static String HUOBI_DEPTH = "/market/depth";

    /********************************HuoBi END***************************/

    /********************************ZB START***************************/
    public final static String REDIS_ZB_FEES_KEY = "zb_fees";

    public final static String REDIS_ZB_SAFE_PASSWORD = "zb_safe_password";

    public final static String ZB_URL_PREFIX = "http://api.zb.com";

    //ZB市场深度接口地址
    public final static String ZB_DEPTH = "/data/v1/depth";

    public final static String ZB_TICKER = "/data/v1/ticker";

    public final static String ZB_WITHDRAW = "/api/withdraw";

    public final static String ZB_BUY = "/api/order";

    /********************************ZB END***************************/

    /********************************OKex START***************************/
    public final static String OKEX_URL_PREFIX = "https://www.okex.com";


    //OKex市场深度接口地址
    public final static String OKEX_DEPTH = "/api/v1/depth.do";

    /********************************Okex END***************************/

    /******************************** BIAN START*******************************/
    public final static String BIAN_URL_PREFIX = "https://api.binance.com";

    //币安市场深度接口地址
    public final static String BIAN_DEPTH = "/api/v1/depth";

    public final static String BIAN_TICKER = "/api/v1/ticker/24hr";

    public final static String BIAN_WITHDRAW = "/wapi/v3/withdraw.html";

    public final static String BIAN_BUY = "/api/v3/order";
}
