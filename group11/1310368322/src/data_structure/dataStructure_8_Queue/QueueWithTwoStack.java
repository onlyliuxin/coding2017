package dataStructure_8_Queue;

import java.util.Stack;

/**
 * 用两个栈来实现一个队列
 * @author liuxin
 * @param <E>
 *
 * @param <E>
 */

public class QueueWithTwoStack<E> {
	
	private Stack<E> stack1;    
    private Stack<E> stack2;    
    private static int i = 0;
    
    public QueueWithTwoStack() {
        stack1 = new Stack<E>();
        stack2 = new Stack<E>();
    }

    public boolean isEmpty() {
        return false;
    }
    
    public int size() {
        return -1;   
    }

    public void enQueue(E item) {
        stack1.push(item);
    }

    public E deQueue() {
    	if(stack2.isEmpty()){
    		moveStack1ToStack2();
    	}
    	if(i!=0){
    		return stack2.pop();
    	}
        return null;
    }

	public void moveStack1ToStack2() {
		while(!stack1.isEmpty()){
			stack2.push(stack1.pop());
			i++;
		}
	}

}
