package org.xukai.coderising.stack;


import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;

public class StackWithTwoQueues {

    private ArrayBlockingQueue<Integer> queue1 = new ArrayBlockingQueue<Integer>(10);

    private ArrayBlockingQueue<Integer> queue2 = new ArrayBlockingQueue<Integer>(10);


	

    public void push(int data) {

        if (queue1.isEmpty()) {
            queue1.offer(data);
            swap(queue1,queue2);
        } else {
            queue2.offer(data);
            swap(queue2, queue1);
        }
    }

    private void swap(ArrayBlockingQueue<Integer> target, ArrayBlockingQueue<Integer> origin) {
        while (!origin.isEmpty()) {
            target.offer(origin.poll());
        }
    }

    public int pop() {
        if (!queue1.isEmpty()) {
            return queue1.poll();
        } else if(!queue2.isEmpty()) {
            return queue2.poll();
        }
       return -1;
    }

    @Test
    public void testStackWithTwoQueues(){
        StackWithTwoQueues queue = new StackWithTwoQueues();
        queue.push(6);
        queue.push(5);
        queue.push(4);
        queue.push(3);
        queue.push(2);
        queue.push(1);
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());

    }

    
}
