package code09;

import code08.Queue;

/**
 * 思路和2个栈实现一个队列是一致的，入的时候简单，出的时候麻烦
 */

public class StackWithTwoQueues {


    Queue q1 = new Queue();
    Queue q2 = new Queue();

    public void push(int data) {
        q1.enQueue(data);
    }

    public int pop() {
        if(q1.isEmpty()){
            return -1;
        }

        if(q1.size() == 1){
            return (Integer) q1.deQueue();
        }

        while (q1.size()>1){
            q2.enQueue(q1.deQueue());
        }

        int result = (Integer) q1.deQueue();

        while (!q2.isEmpty()){
            q1.enQueue(q2.deQueue());
        }

        return result;
    }


}