package com.github.HarryHook.coding2017.stack;

import com.github.HarryHook.coding2017.queue.Queue;

public class StackWithTwoQueues {
    private Queue<Integer> queue1;
    private Queue<Integer> queue2;

    public StackWithTwoQueues() {
	queue1 = new Queue<>(); // 入栈
	queue2 = new Queue<>(); // 出栈
    }

    public boolean isEmpty() {
	if (queue1.isEmpty() && queue2.isEmpty()) {
	    return true;
	}
	return false;
    }

    public int size() {
	return queue1.size() + queue2.size();
    }

    public void push(int data) {
	queue1.enQueue(data);	
    }

    public int pop() {
	if (isEmpty()) {
	    throw new RuntimeException("stack is empty");
	}
	while(!(queue1.isEmpty())) {
	    queue2.enQueue(queue1.deQueue());
	}
	int tmp = 0;
	while(!(queue2.isEmpty())) {
	    tmp = queue2.deQueue();
	    if(queue2.isEmpty()) {
		break;
	    }
	    queue1.enQueue(tmp);
	}
	return tmp;
    }
    public static void main(String[] args) {
	StackWithTwoQueues stack = new StackWithTwoQueues();
	stack.push(1);
	stack.push(2);
	stack.push(3);
	System.out.println(stack.pop());
	stack.push(43);
	System.out.println(stack.pop());
	System.out.println(stack.pop());
	System.out.println(stack.pop());
    }

}
