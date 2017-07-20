package com.coderising.myknowledgepoint.threadpool;

/**
 * Created by thomas_young on 26/6/2017.
 */
public class DriveThreadPool {

    public static void main(String[] args) throws Exception {
        Task task = ()-> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("I'm killed!");
            }
            System.out.println("haha");
        };

        ThreadPool pool = new ThreadPool(5, 2);
        for (int i=1; i<10; i++) {
            pool.execute(task);
        }
        pool.stop();

    }
}
