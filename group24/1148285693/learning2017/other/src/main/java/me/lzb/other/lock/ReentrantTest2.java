package me.lzb.other.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by LZB on 2017/3/30.
 */
public class ReentrantTest2 implements Runnable {
    ReentrantLock lock = new ReentrantLock();

    public void get() {
        lock.lock();
        System.out.println(Thread.currentThread().getId());
        set();
        lock.unlock();
    }

    public void set() {
        lock.lock();
        System.out.println(Thread.currentThread().getId());
        lock.unlock();
    }

    @Override
    public void run() {
        get();
    }

    public static void main(String[] args) {
        ReentrantTest2 ss = new ReentrantTest2();
        new Thread(ss).start();
        new Thread(ss).start();
        new Thread(ss).start();
    }
}