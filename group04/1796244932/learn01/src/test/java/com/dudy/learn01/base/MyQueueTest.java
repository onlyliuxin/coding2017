package com.dudy.learn01.base;

import org.junit.Test;

import static org.junit.Assert.*;

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