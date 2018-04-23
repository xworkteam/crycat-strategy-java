package com.xteam.crycat.base;

public enum RespEnums {
    OK("200", "response success !"),
    UNKNOWN_ERROR("-10", "unknown error !"),
    STRATEGY_INSTANCE_EXIST("-11", "The strategy instance is exist !"),
    STRATEGY_CONFIG_ERROR("-121", "The strategy config error !"),
    EXCHANGES_CONFIG_ERROR("-122", "The exchanges config error !"),
    NO_START_METHOD_ERROR("-14", "There are some error in the strategy code, please check it !"),
    START_STEATEGY_FAILURE("-15", "The strategy start failure !"),

    NO_SUCH_STRATEGY_CODE_ERROR("-15", "The strategy code not find, please create strategy first !"),
    PARAMS_STRATEGY_CODE_ERROR("-17", "The strategy code is null !"),

    NO_STOP_METHOD_ERROR("-18", "There are some error in the strategy code, please check it !"),
    STOP_STEATEGY_FAILURE("-19", "The strategy stop failure, please try again !");

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