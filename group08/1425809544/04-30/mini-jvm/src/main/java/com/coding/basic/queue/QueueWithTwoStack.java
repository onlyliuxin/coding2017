package com.coding.basic.queue;

import java.util.Stack;

/**
 * Created by 14258 on 2017/5/6.
 * 入队时，将元素压入s1。
 * 出队时，判断s2是否为空，如不为空，则直接弹出顶元素；如为空，则将s1的元素逐个“倒入”s2，把最后一个元素弹出并出队。
 */
public class QueueWithTwoStack <E>{


    private Stack<E> stack1;
    private Stack<E> stack2;

    public QueueWithTwoStack() {
        this.stack1 = new Stack();
        this.stack2 = new Stack();
    }


    /**
     * 移动stack1倒序到stack2
     */
    private void moveStack1ToStack2() {
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
    }


    public boolean isEmpty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }

    public int size() {
        return stack1.size() + stack2.size();
    }


    /**
     * 进stack
     */
    public void enQueue(E item) {
        stack1.push(item);
    }

    /**
     * 出stack
     */
    public E deQueu() {
        if (isEmpty()) {
            throw new RuntimeException("stack为空");
        }
        if (stack2.isEmpty()) {
            moveStack1ToStack2();
        }
        return stack2.pop();
    }

}
