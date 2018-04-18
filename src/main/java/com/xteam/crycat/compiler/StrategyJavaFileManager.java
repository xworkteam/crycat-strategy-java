package com.xteam.crycat.compiler;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StrategyJavaFileManager extends ForwardingJavaFileManager<JavaFileManager> {

    private Map<String, JavaFileObject> fileObjects = new ConcurrentHashMap<>();

    protected StrategyJavaFileManager(JavaFileManager fileManager) {
        super(fileManager);
    }

    @Override
    public JavaFileObject getJavaFileForInput(Location location, String className, JavaFileObject.Kind kind) throws IOException {
        JavaFileObject javaFileObject = fileObjects.get(className);
        if(javaFileObject == null){
            super.getJavaFileForInput(location, className, kind);
        }
        return javaFileObject;
    }

    @Override
    public JavaFileObject getJavaFileForOutput(Location location, String qualifiedClassName, JavaFileObject.Kind kind, FileObject sibling) throws IOException {
        JavaFileObject javaFileObject = new StrategyJavaFileObject(qualifiedClassName, kind);
        fileObjects.put(qualifiedClassName, javaFileObject);
        return javaFileObject;
    }

    public Map<String, JavaFileObject> getFileObjects(){
        return fileObjects;
    }
}
