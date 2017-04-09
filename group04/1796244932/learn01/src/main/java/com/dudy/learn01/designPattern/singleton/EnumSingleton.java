package com.dudy.learn01.designPattern.singleton;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by dudy on 2017/3/6.
 */
public enum  EnumSingleton {

    SINGLETON;
    private EnumSingleton(){}


    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 6000 * 10, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(5));


        for (int i = 0; i< 16; i++){
            executor.execute(new EnumSingletonTest());
        }

        executor.shutdown();


    }
}


class  EnumSingletonTest implements  Runnable{

    @Override
    public void run() {
        EnumSingleton singleton = EnumSingleton.SINGLETON;
        System.out.println(singleton.hashCode());
    }
}