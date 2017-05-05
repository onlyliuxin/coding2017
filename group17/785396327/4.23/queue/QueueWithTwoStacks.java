package queue;

import stack.Stack;

/**
 * Created by gongxun on 2017/4/24.
 */
public class QueueWithTwoStacks<E> {
    private Stack<E> stack1;
    private Stack<E> stack2;


    public QueueWithTwoStacks() {
        stack1 = new Stack<E>();
        stack2 = new Stack<E>();
    }


    public boolean isEmpty() {
        return stack1.size() == 0;
    }


    public int size() {
        return stack1.size();
    }


    public void enQueue(E item) {
        Stack<E> temp = new Stack<E>();
        stack1.push(item);
        while (!stack2.isEmpty())
            temp.push(stack2.pop());
        temp.push(item);
        while (!temp.isEmpty())
            stack2.push(temp.pop());
    }

    public E deQueue() {
        Stack<E> temp = new Stack<E>();
        E ele = stack2.pop();
        while (!stack1.isEmpty())
            temp.push(stack1.pop());
        temp.pop();
        while (!temp.isEmpty())
            stack1.push(temp.pop());
        return ele;
    }

    @Override
    public String toString() {
        return stack1.toString() + "\n" + stack2.toString();
    }
}
