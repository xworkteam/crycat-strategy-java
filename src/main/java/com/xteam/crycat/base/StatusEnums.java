package com.xteam.crycat.base;

public enum StatusEnums {

    CREATED(0, "created"),
    RUNNING(1, "running"),
    PAUSE(2, "pause");

    private int code;
    private String msg;

    StatusEnums(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
