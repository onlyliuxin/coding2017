package rui.study.coding2017;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 测试队列
 * Created by 赵睿 on 2017/2/25.
 */
public class QueueTest {
    @Test
    public void enQueue() throws Exception {
        Queue queue=new Queue();
        queue.enQueue(1);
        queue.enQueue(2);
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
    }


    @Test
    public void isEmpty() throws Exception {
        Queue queue=new Queue();
        System.out.println(queue.isEmpty());
        queue.enQueue(1);
        System.out.println(queue.isEmpty());
    }

    @Test
    public void size() throws Exception {
        Queue queue=new Queue();
        System.out.println(queue.size());
        queue.enQueue(1);
        System.out.println(queue.size());
    }

}