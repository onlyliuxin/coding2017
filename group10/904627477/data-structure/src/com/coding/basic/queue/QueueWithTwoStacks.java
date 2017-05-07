package com.coding.basic.queue;

import java.util.Stack;

public class QueueWithTwoStacks<E> {
	private Stack<E> stack1;
	private Stack<E> stack2;

	public QueueWithTwoStacks() {
		stack1 = new Stack<E>();
		stack2 = new Stack<E>();
	}

	public boolean isEmpty() {
		if(stack1.size()==0&&stack2.size()==0){
			return true;
		}
		return false;
	}

	public int size() {
		return stack1.size()+stack2.size();
	}

	public void enQueue(E item) {
		stack1.push(item);
	}

	public E deQueue() {
		if (stack2.isEmpty()) {
			while (!stack1.isEmpty()) {
				stack2.push(stack1.pop());
			}
		}
		return stack2.pop();
	}

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer();
		while(!this.isEmpty()){
			if(buff.length()!=0){
				buff.append(",");
			}
			buff.append(this.deQueue());
		}
		return buff.toString();
	}
	
	

}
