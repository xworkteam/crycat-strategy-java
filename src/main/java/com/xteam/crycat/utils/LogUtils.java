package com.xteam.crycat.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description 日志工具类
 * @package com.xteam.crycat.utils
 * @author alyenc
 * @email alyenc@outlook.com
 * @date 2018/1/31 下午3:11
 * @version v1.0.0
 */
public final class LogUtils {

    /**
     * @description 获得Logger
     * @author alyenc
     * @date 2018/1/31 下午2:59
     * @param clazz
     * @return Logger
     */
    public static Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }

    /**
     * @description 获得Logger
     * @author alyenc
     * @date 2018/1/31 下午2:59
     * @param name
     * @return Logger
     */
    public static Logger getLogger(String name) {
        return LoggerFactory.getLogger(name);
    }

    /**
     * @description 获得Logger，自动判定日志发出者
     * @author alyenc
     * @date 2018/1/31 下午3:00
     * @return Logger
     */
    public static Logger getLogger() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        return LoggerFactory.getLogger(stackTrace[2].getClassName());
    }

    /**
     * @description Trace等级日志，级别小于Debug
     * @author alyenc
     * @date 2018/1/31 下午3:01
     * @param format 日志格式，{} 代表变量
     * @param arguments 日志变量可变参数
     */
    public static void trace(String format, Object... arguments) {
        trace(innerGet(), format, arguments);
    }

    /**
     * @description Trace等级日志，级别小于Debug
     * @author alyenc
     * @date 2018/1/31 下午3:02
     * @param log 日志对象
     * @param format 日志格式，{} 代表变量
     * @param arguments 变量对应的可变参数
     */
    public static void trace(Logger log, String format, Object... arguments) {
        log.trace(format, arguments);
    }

    /**
     * @description Debug等级日志，小于Info
     * 由于动态获取Logger，效率较低，建议在非频繁调用的情况下使用！！
     * @author alyenc
     * @date 2018/1/31 下午3:03
     * @param format 日志格式，{} 代表变量
     * @param arguments 变量对应的参数
     */
    public static void debug(String format, Object... arguments) {
        debug(innerGet(), format, arguments);
    }

    /**
     * @description Debug等级日志，小于Info
     * @author alyenc
     * @date 2018/1/31 下午3:04
     * @param log 日志对象
     * @param format 日志格式，{} 代表变量
     * @param arguments 变量对应的可变参数
     */
    public static void debug(Logger log, String format, Object... arguments) {
        log.debug(format, arguments);
    }

    /**
     * @description Info等级日志，小于Warn
     * 由于动态获取Logger，效率较低，建议在非频繁调用的情况下使用！！
     * @author alyenc
     * @date 2018/1/31 下午3:05
     * @param format 日志格式，{} 代表变量
     * @param arguments 变量对应的可变参数
     */
    public static void info(String format, Object... arguments) {
        info(innerGet(), format, arguments);
    }

    /**
     * @description Info等级日志，小于Warn
     * @author alyenc
     * @date 2018/1/31 下午3:06
     * @param log 日志对象
     * @param format 日志格式，{} 代表变量
     * @param arguments 变量对应的可变参数
     */
    public static void info(Logger log, String format, Object... arguments) {
        log.info(format, arguments);
    }

    /**
     * @description Warn等级日志，小于Error
     * 由于动态获取Logger，效率较低，建议在非频繁调用的情况下使用！！
     * @author alyenc
     * @date 2018/1/31 下午3:06
     * @param format 日志格式，{} 代表变量
     * @param arguments 变量对应的可变参数
     */
    public static void warn(String format, Object... arguments) {
        warn(innerGet(), format, arguments);
    }

    /**
     * @description Warn等级日志，小于Error
     * @author alyenc
     * @date 2018/1/31 下午3:07
     * @param log 日志对象
     * @param format 格式文本，{} 代表变量
     * @param arguments 变量对应的参数
     */
    public static void warn(Logger log, String format, Object... arguments) {
        log.warn(format, arguments);
    }

    /**
     * @description Warn等级日志，小于Error<br>
     * 由于动态获取Logger，效率较低，建议在非频繁调用的情况下使用！！
     * @author alyenc
     * @date 2018/1/31 下午3:07
     * @param e 需在日志中堆栈打印的异常
     * @param format 格式文本，{} 代表变量
     * @param arguments 变量对应的参数
     */
    public static void warn(Throwable e, String format, Object... arguments) {
        warn(innerGet(), e, format(format, arguments));
    }

    /**
     * @description Warn等级日志，小于Error
     * @author alyenc
     * @date 2018/1/31 下午3:07
     * @param log 日志对象
     * @param e 需在日志中堆栈打印的异常
     * @param format 格式文本，{} 代表变量
     * @param arguments 变量对应的参数
     */
    public static void warn(Logger log, Throwable e, String format, Object... arguments) {
        log.warn(format(format, arguments), e);
    }

    /**
     * @description Error等级日志
     * 由于动态获取Logger，效率较低，建议在非频繁调用的情况下使用！！
     * @author alyenc
     * @date 2018/1/31 下午3:07
     * @param format 格式文本，{} 代表变量
     * @param arguments 变量对应的参数
     */
    public static void error(String format, Object... arguments) {
        error(innerGet(), format, arguments);
    }

    /**
     * @description Error等级日志
     * @author alyenc
     * @date 2018/1/31 下午3:07
     * @param log 日志对象
     * @param format 格式文本，{} 代表变量
     * @param arguments 变量对应的参数
     */
    public static void error(Logger log, String format, Object... arguments) {
        log.error(format, arguments);
    }

    /**
     * @description Error等级日志
     * 由于动态获取Logger，效率较低，建议在非频繁调用的情况下使用！！
     * @author alyenc
     * @date 2018/1/31 下午3:07
     * @param e 需在日志中堆栈打印的异常
     * @param format 格式文本，{} 代表变量
     * @param arguments 变量对应的参数
     */
    public static void error(Throwable e, String format, Object... arguments) {
        error(innerGet(), e, format(format, arguments));
    }

    /**
     * @description Error等级日志
     * 由于动态获取Logger，效率较低，建议在非频繁调用的情况下使用！！
     * @author alyenc
     * @date 2018/1/31 下午3:07
     * @param log 日志对象
     * @param e 需在日志中堆栈打印的异常
     * @param format 格式文本，{} 代表变量
     * @param arguments 变量对应的参数
     */
    public static void error(Logger log, Throwable e, String format, Object... arguments) {
        log.error(format(format, arguments), e);
    }

    /**
     * @description 格式化文本
     * @author alyenc
     * @date 2018/1/31 下午3:07
     * @param template 文本格式，被替换的部分用 {} 表示
     * @param values 参数值
     * @return String 格式化后的文本
     */
    private static String format(String template, Object... values) {
        return String.format(template.replace("{}", "%s"), values);
    }

    /**
     * @description 获得日志，自动判定日志发出者
     * @author alyenc
     * @date 2018/1/31 下午3:08
     * @return Logger
     */
    private static Logger innerGet() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        return LoggerFactory.getLogger(stackTrace[3].getClassName());
    }
}
