package com.coderising.myknowledgepoint.concurrency;

/**
 * Created by thomas_young on 10/11/2017.
 */
class Unsafe {
    public int foo = 15;
    public Unsafe(Unsafe[] leak, int loop) {
        leak[0] = this;   // Unsafe publication
        // Make the "window of vulnerability" large
        for (long l = 1; l < loop /* very large */ ; l++) {
            l++;
        }
        foo = 42;
    }
}

/**
 * 修改LOOP,会发现leak可能在Unsafe还未初始化前把状态泄露出去
 * 根本原因是Thread中Unsafe对象还未运行结束，主线程中的leak就已经在使用该对象的状态了
 */
public class EscapeDemo {
    public static void main(String[] args) throws InterruptedException {
        final int LOOP = 1;  // loop=100000或1
        final Unsafe[] leak = new Unsafe[1];
        new Thread(() -> {
            Thread.yield();   // (or sleep for a bit)
            new Unsafe(leak, LOOP);
        }).start();

        while (true) {

            if (leak[0] != null) {
                if (leak[0].foo == 42) {
                    System.err.println("OK");
                } else {
                    System.err.println("OUCH!");
                }
                System.exit(0);
            }
        }
    }
}
