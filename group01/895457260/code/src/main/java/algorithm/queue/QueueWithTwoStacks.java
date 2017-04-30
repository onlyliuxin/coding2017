package algorithm.queue;

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
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public boolean isEmpty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }

    public int size() {
        return Math.max(stack1.size(), stack2.size());
    }

    public void enQueue(E item) {
        int size = stack2.size();
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
        stack2.push(item);
        for (int i = 0; i < size; ++i) {
            stack2.push(stack1.pop());
        }
        stack1.push(item);
    }

    public E deQueue() {
        E e = stack2.pop();
        int size = stack2.size();
        for (int i = 0; i < size; ++i) {
            stack2.push(stack1.pop());
        }
        stack1.pop();
        for (int i = 0; i < size; ++i) {
            stack1.push(stack2.pop());
        }
        return e;
    }
}
