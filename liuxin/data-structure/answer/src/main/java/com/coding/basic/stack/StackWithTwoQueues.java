package com.coding.basic.stack;

import java.util.ArrayDeque;
import java.util.Queue;

public class StackWithTwoQueues {
	Queue<Integer> queue1 = new ArrayDeque<>();
    Queue<Integer> queue2 = new ArrayDeque<>();

    public void push(int data) {
        //两个栈都为空时，优先考虑queue1
        if (queue1.isEmpty()&&queue2.isEmpty()) {
            queue1.add(data);
            return;
        }
       
        if (queue1.isEmpty()) {
            queue2.add(data);
            return;
        }

        if (queue2.isEmpty()) {
            queue1.add(data);
            return;
        }

    }

    public int pop() {
        
        if (queue1.isEmpty()&&queue2.isEmpty()) {
        	throw new RuntimeException("stack is empty");
        } 
        
        if (queue1.isEmpty()) {
            while (queue2.size()>1) {
                queue1.add(queue2.poll());
            }
            return queue2.poll();
        } 
        
        if (queue2.isEmpty()) {
            while (queue1.size()>1) {
                queue2.add(queue1.poll());
            }
            return queue1.poll();
        }
        
        throw new RuntimeException("no queue is empty, this is not allowed");
        

    }
}
