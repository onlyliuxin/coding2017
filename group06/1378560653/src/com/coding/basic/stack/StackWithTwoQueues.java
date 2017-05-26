package com.coding.basic.stack;

import com.coding.basic.queue.Queue;

/**
 * 用两个队列来实现一个栈
 * @author kai
 *
 * @param <E>
 */

public class StackWithTwoQueues <E> {
	private Queue<E> queue1;
	private Queue<E> queue2;
	
	public StackWithTwoQueues() {
		queue1 = new Queue<>();
		queue2 = new Queue<>();
		
	}
	
	public boolean isEmpty(){
		return queue1.size() == 0;
	}
	
	public int size(){
		return queue1.size();
	}
	
    public void push(E data) {       
    	queue1.enQueue(data);
    }

    public E pop() {
    	if(isEmpty()){
    		throw new RuntimeException("Stack is empty");
    	}
    	
    	E item = null;
    	while(!queue1.isEmpty()){
    		item = queue1.deQueue();
    		if(!queue1.isEmpty()){
    			queue2.enQueue(item); //最后一个入队的释放，不再放入第二个队列	
    		}
    	}
    
    	while(!queue2.isEmpty()){
    		queue1.enQueue(queue2.deQueue());
    	}
    	return item;
    }
    
    public static void main(String[] args){
    	StackWithTwoQueues<Integer> stack = new StackWithTwoQueues<>();
    	stack.push(1);
    	stack.push(2);
    	stack.push(3);
    	stack.push(4);
    	while(!stack.isEmpty()){
    		System.out.println(stack.pop());
    	}
    	
    }

    
}
