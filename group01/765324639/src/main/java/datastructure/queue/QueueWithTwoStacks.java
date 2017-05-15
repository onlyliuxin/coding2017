package datastructure.queue;

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

    
    public QueueWithTwoStacks() {
        stack1 = new Stack<E>();
        stack2 = new Stack<E>();
    }

   
    

    public boolean isEmpty() {
        if (stack1.empty() && stack2.empty()) {
            return true;
        }
        return false;
    }


    
    public int size() {
        return stack1.size() + stack2.size();   
    }



    public void enQueue(E item) {
        stack1.push(item);
    }

    public E deQueue() {
        if (isEmpty()) {
            return null;
        }
        
        if (!stack2.empty()) {
            return stack2.pop();
        }
        while (!stack1.empty()) {
            stack2.push(stack1.pop());
        }
        return stack2.pop();
    }



 }

