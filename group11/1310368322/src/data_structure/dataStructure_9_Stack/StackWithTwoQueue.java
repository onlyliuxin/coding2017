package dataStructure_9_Stack;

import java.util.Stack;

import javax.management.RuntimeErrorException;

/*
 * 用两个队列实现一个栈
 */

public class StackWithTwoQueue {

	Queue<Integer> queue1 = new Queue<Integer>();
	Queue<Integer> queue2 = new Queue<Integer>();
	
	public void push(int data) {       
		queue1.enQueue(data);
	}

    public int pop() {
    	if(queue1.isEmpty()){
    		throw new RuntimeException("栈为空");
    	}
    	moveQueue1ToQueue2LeftLastOne();
    	int pop = queue1.deQueue();
    	moveQueue2ToQueue1();
        return pop;
    }

	private void moveQueue2ToQueue1() {
		while(!queue2.isEmpty()){
			queue1.enQueue(queue2.deQueue());
		}
		
	}

	private void moveQueue1ToQueue2LeftLastOne() {
		while(queue1.size()!=1){
			queue2.enQueue(queue1.deQueue());;
		}
	}
}
