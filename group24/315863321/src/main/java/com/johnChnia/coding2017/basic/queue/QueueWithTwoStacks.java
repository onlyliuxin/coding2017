package com.johnChnia.coding2017.basic.queue;


import com.johnChnia.coding2017.basic.stack.Stack;

/**
 * Created by john on 2017/5/6.
 *
 * 参考：https://leetcode.com/articles/implement-queue-using-stacks/
 */

public class QueueWithTwoStacks<E> {
    private Stack<E> stack1;
    private Stack<E> stack2;


    public QueueWithTwoStacks() {
        stack1 = new Stack<E>();  // 队列
        stack2 = new Stack<E>();
    }


    public boolean isEmpty() {
        if (stack1.empty()) {
            return true;
        }
        return false;
    }


    public int size() {
        if (stack1.empty()) {
            return -1;
        }
        return stack1.size();
    }


    /**
     * 如果stack1为空，则直接加到stack1中，如果不为空，首先遍历stack1中元素全放入stack2中，再把item加入
     * stack1中，最后再遍历stack2中元素重新放回stack1中。
     *
     * @param item 入队值
     */
    public void enQueue(E item) {
        while (!stack1.empty()) {
            stack2.push(stack1.pop());
        }
        stack1.push(item);
        while (!stack2.empty()) {
            stack1.push(stack2.pop());
        }
    }

    /**
     * 出队列操作
     *
     * @return 如果队列为空就返回null，否则根据先进先出原则返回元素。
     */
    public E deQueue() {
        if (isEmpty()) {
            return null;
        }
        return stack1.pop();
    }

    public String toString() {
        return stack1.toString();
    }
}

