package me.lzb.other.lock;

/**
 * Created by LZB on 2017/3/30.
 */
public class ReentrantTest1 implements Runnable{

    public synchronized void get(){
        System.out.println(Thread.currentThread().getId());
        set();
    }

    public synchronized void set(){
        System.out.println(Thread.currentThread().getId());
    }

    @Override
    public void run() {
        get();
    }
    public static void main(String[] args) {
        ReentrantTest1 ss=new ReentrantTest1();
        new Thread(ss).start();
        new Thread(ss).start();
        new Thread(ss).start();
    }
}