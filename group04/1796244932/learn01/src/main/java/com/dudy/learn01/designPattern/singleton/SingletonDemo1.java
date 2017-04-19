package com.dudy.learn01.designPattern.singleton;

import java.io.Serializable;
import java.util.concurrent.*;

/**
 * Created by dudy on 2017/3/6.
 * 双检索 方式
 * jdk1.5 以后 其实是线程安全的。
 * 序列化会 破坏 单利
 *
 */
public class SingletonDemo1 implements Serializable{

    private  static volatile SingletonDemo1  singleton  = null; // 加 volatile 是为了 可见性，另一个就是 避免重排序

    private  SingletonDemo1(){}

    public  static   SingletonDemo1 getIntance(){

        if (singleton == null){// 第一个避免 在 synchronized 中 一直排队
            synchronized (SingletonDemo1.class){

                if (singleton == null){// 如果对象为空，才被创建
                    singleton = new SingletonDemo1();
                }

            }
        }

        return  singleton;
    }


    /**
     * 解决 反序列化的问题
     * @return
     */
    private Object readResolve() {
        return singleton;
    }

    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        for (int i= 0 ;i < 5; i++){
            threadPool.execute(new TestRunable());
        }

        threadPool.shutdown();

        //new ThreadPoolExecutor(10,20,1000*2,new BlockingQueue<Thread>(),)


    }

}

 class   TestRunable  implements  Runnable{

     public void run() {
         SingletonDemo1 intance = SingletonDemo1.getIntance();
         System.out.println(intance.hashCode());
     }
 }
