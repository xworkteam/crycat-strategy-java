package com.xteam.crycat.compiler;

import javax.tools.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrategyCompiler {

    private String code;

    private ConcurrentHashMap<String, Method> methods = new ConcurrentHashMap<>();

    private Class<?> clazz = null;

    public StrategyCompiler(String code){
        this.code = code;
    }

    public void compile() {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> collector = new DiagnosticCollector<>();
        StrategyJavaFileManager javaFileManager = new StrategyJavaFileManager(compiler.getStandardFileManager(collector, null, null));

        List<String> options = new ArrayList<>();
        options.add("-target");
        options.add("1.8");

        Pattern CLASS_PATTERN = Pattern.compile("class\\s+([$_a-zA-Z][$_a-zA-Z0-9]*)\\s*");
        Matcher matcher = CLASS_PATTERN.matcher(code);
        String cls;
        if (matcher.find()) {
            cls = matcher.group(1);
        } else {
            throw new IllegalArgumentException("No such class name in " + code);
        }

        JavaFileObject javaFileObject = new StrategyJavaFileObject(cls, code);
        Boolean result = compiler.getTask(null, javaFileManager, collector, options, null, Arrays.asList(javaFileObject)).call();
        ClassLoader classloader = new StrategyClassLoader(javaFileManager.getFileObjects());

        try {
            clazz = classloader.loadClass(cls);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public static void main(String[] args) throws Exception{
//        StrategyCompiler compiler = new StrategyCompiler();
//        compiler.compile();
//        ConcurrentHashMap<String, Method> methods = compiler.getMethods();
//        methods.get("start").invoke(compiler.getClazz().newInstance());
    }
}
