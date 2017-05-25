package task0430.coding.basic.queue;

import java.util.Stack;

/**
 * 用两个栈来实现一个队列
 * @author liuxin
 *
 * @param <E>
 */
public class QueueWithTwoStacks<E> {
	private Stack<E> stack1;    
    private Stack<E> stack2;
	private int size;    

    
    public QueueWithTwoStacks() {
        stack1 = new Stack<E>();
        stack2 = new Stack<E>();
    }

   
    

    public boolean isEmpty() {
        return stack1.isEmpty();
    }


    
    public int size() {
        return size;   
    }



    public void enQueue(E item) {
        stack1.push(item);
        size++;
    }

    public E deQueue() {
    	if(stack1.isEmpty()){
    		throw new RuntimeException("queue is empty.");
    	}
    	
    	while(!stack1.isEmpty()){
    		stack2.push(stack1.pop());
    	}
    	
    	E result = stack2.pop();
    	size--;
    	
    	while(!stack2.isEmpty()){
    		stack1.push(stack2.pop());
    	}
        return result;
    }
    
    public String toString (){
    	StringBuilder sb = new StringBuilder();
    	sb.append("[");
    	while(!stack1.isEmpty()){
    		stack2.push(stack1.pop());
    	}
    	while(!stack2.isEmpty()){
    		E element = stack2.pop();
    		if(stack2.isEmpty()){
        		sb.append(element.toString());
        		stack1.push(element);
        		break;
    		}
    		sb.append(element.toString()+", ");    			
    		stack1.push(element);
    	}
    
    	return sb.toString()+"]";
    }


 }
