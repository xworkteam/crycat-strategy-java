package com.xteam.crycat.strategy;

import com.xteam.crycat.strategy.code.BaseStrategyCode;

public class StrategyExecutor implements Runnable {

    private Thread thread;
    private String name;
    private final BaseStrategyCode strategy;
    private volatile boolean suspended = false;
    private volatile boolean running = true;

    public StrategyExecutor(String name, BaseStrategyCode strategy){
        this.name = name;
        this.strategy = strategy;
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
                        strategy.execute();
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
        if(thread==null){
            thread = new Thread(this, name);
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
