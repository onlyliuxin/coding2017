package structure.week9;

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
        return stack1.isEmpty();
    }
    public int size() {
        return stack1.size();
    }
    public void enQueue(E item) {
        stack1.push(item);
    }
    public E deQueue() {
        while(stack1.size()>1)
        	stack2.push(stack1.pop());
        E res = stack1.pop();
        while(stack2.size()>0)
        	stack1.push(stack2.pop());
        return res;
    }
 }

