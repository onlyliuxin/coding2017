package com.dudy.learn01.data_structure.base;

import com.dudy.learn01.data_structure.queue.MyQueue;
import org.junit.Test;

/**
 * Created by dudy on 2017/2/20.
 */
public class MyQueueTest {
    @Test
    public void enQueue() throws Exception {


        MyQueue queue = new MyQueue();
        queue.enQueue("1");
//        queue.enQueue("2");
//
//        queue.enQueue("3");

        while (queue.size() > 0){
            System.out.println(queue.deQueue());
        }

    }

}