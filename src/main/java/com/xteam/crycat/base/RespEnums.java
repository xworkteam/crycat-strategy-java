package com.xteam.crycat.base;

public enum RespEnums {
    OK("200", "response success !"),
    UNKNOWN_ERROR("-10", "unknown error !"),
    STRATEGY_INSTANCE_EXIST("-11", "The strategy instance is exist !"),
    STRATEGY_PARAMS_ERROR("-12", "The strategy params error !"),
    NO_START_METHOD_ERROR("-13", "There are some error in the strategy code, please check it !"),
    START_STEATEGY_FAILURE("-14", "The strategy start failure !"),

    NO_SUCH_STRATEGY_CODE("-15", "The strategy code not find, please create strategy first !"),
    PARAMS_STRATEGY_CODE("-16", "The strategy code is null !"),

    NO_STOP_METHOD_ERROR("-17", "There are some error in the strategy code, please check it !"),
    STOP_STEATEGY_FAILURE("-18", "The strategy stop failure, please try again !");

    private String code;
    private String msg;

    RespEnums(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}