package com.dudy.learn01.designPattern.singleton;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by dudy on 2017/3/6.
 *
 * 静态内部类方式 实现
 *
 * 这种方式同样利用了classloder的机制来保证初始化instance时只有一个线程，
 * 它跟饿汉式不同的是（很细微的差别）：饿汉式是只要Singleton类被装载了，
 * 那么instance就会被实例化（没有达到lazy loading效果），而这种方式是Singleton类被装载了，
 * instance不一定被初始化。因为SingletonHolder类没有被主动使用，
 * 只有显示通过调用getInstance方法时，才会显示装载SingletonHolder类，
 * 从而实例化instance。想象一下，如果实例化instance很消耗资源，我想让他延迟加载，
 * 另外一方面，我不希望在Singleton类加载时就实例化，
 * 因为我不能确保Singleton类还可能在其他的地方被主动使用从而被加载，
 * 那么这个时候实例化instance显然是不合适的。这个时候，这种方式相比饿汉式更加合理
 *
 */
public class StaticClassInnerSingleton {

    // 构造器 私有化
    private StaticClassInnerSingleton(){}

    private static  class   SingletonHolder{
        private  static  final StaticClassInnerSingleton INSTANCE  = new StaticClassInnerSingleton();
    }


    public  static StaticClassInnerSingleton getInstance(){
        return  SingletonHolder.INSTANCE;
    }


    public static void main(String[] args) {


        ThreadPoolExecutor pool = new ThreadPoolExecutor(10, 10,
                6000 * 10, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<Runnable>());

        for (int i= 0; i<20; i++){
            pool.execute(new StaicSingletonTest());
            //System.out.println(StaticClassInnerSingleton.getInstance().hashCode());
        }

        pool.shutdown();
    }



}

class   StaicSingletonTest  implements  Runnable{

    public void run() {
        StaticClassInnerSingleton intance = StaticClassInnerSingleton.getInstance();
        System.out.println(intance.hashCode());
    }
}