package main.coding_170430;

import java.util.Stack;

/**
 * Created by peterchen on 2017/5/5.
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
        if (isEmpty()) {
            return null;
        }
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        E data = stack2.pop();
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
        return data;
    }
}
