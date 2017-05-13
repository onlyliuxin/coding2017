package basic.dataStructure.queue;

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
        return stack1.isEmpty() && stack1.isEmpty();
    }

    public int size() {
        return stack1.size() + this.stack2.size();
    }


    public void enQueue(E item) {
        stack1.push(item);
    }

    private void move(){
        while (!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
    }

    public E deQueue() {
        if(stack1.isEmpty()){
            throw new RuntimeException("queue is null");
        }

        if(stack2.isEmpty()){
            move();
        }

        return stack2.pop();
    }
}

