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
        return stack2.size();
    }


    public void enQueue(E item) {
        stack1.push(item);
        Stack<E> tmp = stack1;
        stack2.clear();
        while (!tmp.isEmpty()){
            stack2.push(tmp.pop());
        }
    }

    public E deQueue() {
        return stack2.pop();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Stack<E> tmp = stack2;
        while(!tmp.isEmpty()){
            sb.append(tmp.pop()).append(",");
        }
        sb.substring(0, sb.length() - 1);
        sb.append("]");

        return sb.toString();
    }
}

