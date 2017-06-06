package com.pan.jvm.chapter13;

import java.util.Vector;

/**
 * Created by QiPan on 2017/5/12.
 * Vector 线程安全测试
 */
public class VectorTest {

    private static Vector<Integer> vector = new Vector<>();

    /**
     * 虽然Vector是线程安全的，但是也有可能出现
     * java.lang.ArrayIndexOutOfBoundsException: Array index out of range: 13
     *                                            at java.util.Vector.get(Vector.java:748)
     *                                            at com.pan.jvm.chapter13.VectorTest.lambda$1(VectorTest.java:31)
     *                                            at java.lang.Thread.run(Thread.java:745)
     * @param args
     */
    public static void main_1(String[] args) {
        while (true){
            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }
            Thread removeThread = new Thread(() -> {
                for (int i = 0; i < vector.size(); i++) {
                    vector.remove(i);
                }
            });
            Thread printThread = new Thread(() -> {
                for (int i = 0; i < vector.size(); i++) {
                    System.out.println(vector.get(i));
                }
            });
            removeThread.start();
            printThread.start();

            // 不要同时产生过多的线程，否则会导致操作系统假死
            while (Thread.activeCount() > 20);
        }
    }


    /**
     * 必须加入同步以保证Vector访问的线程安全性
     * @param args
     */
    public static void main_2(String[] args) {

        while (true){
            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }
            Thread removeThread = new Thread(() -> {
                synchronized(vector){
                    for (int i = 0; i < vector.size(); i++) {
                        vector.remove(i);
                    }
                }

            });
            Thread printThread = new Thread(() -> {
                synchronized(vector){
                    for (int i = 0; i < vector.size(); i++) {
                        System.out.println(vector.get(i));
                    }
                }
            });
            removeThread.start();
            printThread.start();

            // 不要同时产生过多的线程，否则会导致操作系统假死
            while (Thread.activeCount() > 20);
        }
    }

    /**
     * 可重入锁
     * @param args
     */
    public static void main_4(String[] args) {

        Thread test = new Thread(() -> {
            // 用循环的方式不能和递归等价，循环一次，代码退出函数，相当于重新获取锁
//            int n = 50;
//            while (n >= 0) {
//                test(n);
//                n--;
//            }
            test(50);

        });
        test.start();
    }

    public synchronized static void test(int n){
        System.out.println(n);
        if (n == 0){
            return;
        }
        // 递归方式
        test(--n);
    }

    public static void main(String[] args) {
        String a = null;
        String b = null;
        a = a == null ? "" : a;
//        b = b == null ? "" : b;
        if (a.equals(b)){
            System.out.println("yyyy");
        }else {
            System.out.println("xxxxxx");
        }
    }

}
