package task8.queue;

import java.util.Stack;

public class QueueWithTwoStacks<E> {

    private Stack<E> stack1;
    private Stack<E> stack2;


    public QueueWithTwoStacks() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
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
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        E itemToPop = stack2.pop();
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
        return itemToPop;
    }
}
