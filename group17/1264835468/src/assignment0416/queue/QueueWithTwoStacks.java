package assignment0416.queue;

import java.util.Stack;

/**
 * 用两个栈来实现一个队列
 *
 * @param <E>
 * @author liuxin
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
        popAll(stack1, stack2);
        E data = stack2.pop();
        popAll(stack2, stack1);
        return data;
    }

    private void popAll(Stack stack1, Stack stack2) {
        for (int i = 0; i < stack1.size(); i++) {
            stack2.push(stack1.pop());
        }
    }


}

