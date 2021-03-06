package com.xteam.crycat.compiler;

import javax.tools.SimpleJavaFileObject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

public class StrategyJavaFileObject extends SimpleJavaFileObject {

    private String source;
    private ByteArrayOutputStream outPutStream;


    public StrategyJavaFileObject(String name, String source) {
        super(URI.create("String:///" + name + Kind.SOURCE.extension), Kind.SOURCE);
        this.source = source;
    }

    public StrategyJavaFileObject(String name, Kind kind){
        super(URI.create("String:///" + name + kind.extension), kind);
        source = null;
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors){
        if(source == null){
            throw new IllegalArgumentException("source == null");
        }
        return source;
    }

    @Override
    public OutputStream openOutputStream() throws IOException {
        outPutStream = new ByteArrayOutputStream();
        return outPutStream;
    }

    public byte[] getCompiledBytes(){
        return outPutStream.toByteArray();
    }
}
