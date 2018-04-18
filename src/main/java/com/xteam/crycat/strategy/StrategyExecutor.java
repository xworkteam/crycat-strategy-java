package com.xteam.crycat.strategy;

import java.lang.reflect.Method;

public class StrategyExecutor implements Runnable {

    private Thread thread;
    private String name;
    private final Class<?> clazz;
    private volatile boolean suspended = false;
    private volatile boolean running = true;
    public StrategyExecutor(String name, Class<?> clazz){
        this.name = name;
        this.clazz = clazz;
    }

    @Override
    public void run() {
        try {
            while (running) {
                synchronized (this) {
                    if (suspended) {
                        wait();
                    }
                    try {
                        Thread.sleep(1000);
                        Method method = clazz.getMethod("main");
                        method.invoke(clazz.newInstance());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }catch (InterruptedException e) {
            System.out.println("Thread " +  name + " interrupted.");
        }
    }

    public void start(){
        System.out.println("Starting " +  name );
        if(thread==null){
            thread=new Thread(this, name);
            thread.start();
        }
    }

    public void stop() {
        this.running = false;
    }

    public void suspend(){
        this.suspended = true;
    }

    public synchronized void resume(){
        this.suspended = false;
        notify();    //唤醒线程
    }
}
