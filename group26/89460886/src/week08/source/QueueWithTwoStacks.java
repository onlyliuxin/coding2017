package queue;

import java.util.Stack;

/**
 * @author jiaxun
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
        return stack1.isEmpty() ? stack2.size() : stack1.size();
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

}
