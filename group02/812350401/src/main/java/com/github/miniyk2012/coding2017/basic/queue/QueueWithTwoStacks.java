package com.github.miniyk2012.coding2017.basic.queue;

import java.util.Stack;

/**
 * 用两个栈来实现一个队列
 * @author liuxin
 *
 * @param <E>
 */
public class QueueWithTwoStacks<E> {
	private Stack<E> stack1;    // stack1用来入队
    private Stack<E> stack2;    // stack2用来出对

    
    public QueueWithTwoStacks() {
        stack1 = new Stack<E>();
        stack2 = new Stack<E>();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    private void move(Stack s1, Stack s2) {
        while (!s1.empty()) {
            s2.push(s1.pop());
        }
    }
    
    public int size() {
        return  stack1.size() + stack2.size();
    }

    public void enQueue(E item) {
        stack1.push(item);
    }

    public E deQueueV2() {
        move(stack1, stack2);
        E result = stack2.pop();
        move(stack2, stack1);
        return result;
    }

    // 只有当stack2为空时，才需要把stack1中的元素全部放到stack2中
    public E deQueue() {
        if (stack2.isEmpty()) {
            move(stack1, stack2);
        }
        return stack2.pop();
    }

 }

