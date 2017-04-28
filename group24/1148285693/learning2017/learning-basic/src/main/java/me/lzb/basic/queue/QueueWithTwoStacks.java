package me.lzb.basic.queue;

import java.util.Stack;

/**
 * 用两个栈来实现一个队列
 * Created by LZB on 2017/4/27.
 */
public class QueueWithTwoStacks<E> {
    private Stack<E> stack1;//ASC
    private Stack<E> stack2;//DESC

    private boolean isASC;


    public QueueWithTwoStacks() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
        isASC = false;
    }


    public boolean isEmpty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }


    public int size() {
        return stack1.isEmpty() ? stack2.size() : stack1.size();
    }


    public void enQueue(E item) {

        if (isASC) {
            while (!stack2.isEmpty()) {
                stack1.push(stack2.pop());
            }
            isASC = false;


        }

        stack1.push(item);
    }

    public E deQueue() {

        if (isEmpty()) {
            throw new RuntimeException("empty");
        }

        if (!isASC) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
            isASC = true;
        }

        return stack2.pop();

    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuffer sb = new StringBuffer();
        sb.append("[");


        Object[] array = new Object[size()];

        if (isASC) {
            int i = size() - 1;
            for (E e : stack2) {
                array[i] = e;
                i--;
            }
        } else {
            int i = 0;
            for (E e : stack1) {
                array[i] = e;
                i++;
            }
        }

        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(array[i].toString());
        }


        sb.append("]");
        return sb.toString();
    }
}

