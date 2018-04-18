package com.xteam.crycat.compiler;

import javax.tools.JavaFileObject;
import java.util.Map;

public class StrategyClassLoader extends ClassLoader {

    private  Map<String, JavaFileObject> fileObjects;

    public StrategyClassLoader(Map<String, JavaFileObject> fileObjects){
        this.fileObjects = fileObjects;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        JavaFileObject fileObject = fileObjects.get(name);
        if(fileObject != null){
            byte[] bytes = ((StrategyJavaFileObject)fileObject).getCompiledBytes();
            return defineClass(name, bytes, 0, bytes.length);
        }
        try{
            return ClassLoader.getSystemClassLoader().loadClass(name);
        } catch (Exception e){
            return super.findClass(name);
        }
    }
}
