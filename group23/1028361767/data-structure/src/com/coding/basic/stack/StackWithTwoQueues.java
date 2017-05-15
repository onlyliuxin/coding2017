package com.coding.basic.stack;

import java.util.LinkedList;
import java.util.Queue;

public class StackWithTwoQueues {
	
	Queue<Integer> queue1 = new LinkedList<Integer>();
	Queue<Integer> queue2 = new LinkedList<Integer>();

    public void push(int data) {       
    	queue1.add(data);
    }

    public int pop() {
    	while(queue1.size() > 1){
    		queue2.add(queue1.poll());
    	}
    	queue1.addAll(queue2);
        return queue1.poll();
    }
    
    public static void main(String[] args) {
    	StackWithTwoQueues s = new StackWithTwoQueues();
    	for (int i = 0; i < 5; i++) {
			s.push(i);
		}
    	System.out.println(s.pop());
    	System.out.println(s.pop());
    	System.out.println(s.pop());
    	System.out.println(s.pop());
	}
    
}
