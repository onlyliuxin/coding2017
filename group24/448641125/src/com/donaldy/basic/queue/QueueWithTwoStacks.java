package com.donaldy.basic.queue;

import java.util.Stack;

/**
 * 用两个栈来实现一个队列
 * @author liuxin
 *
 * @param <E>
 */
public class QueueWithTwoStacks<E> {

    //用于存储
	private Stack<E> stack1;
	//用于操作
    private Stack<E> stack2;

    public QueueWithTwoStacks() {
        stack1 = new Stack<E>();
        stack2 = new Stack<E>();
    }


    public boolean isEmpty() {

        return this.stack1.isEmpty();

    }


    public int size() {

        return this.stack1.size();

    }


    public void enQueue(E item) {
        this.stack1.push(item);
    }

    public E deQueue() {

        if (this.size() <= 0) {
            throw new IndexOutOfBoundsException("size : " + this.size());
        }

        while (!this.stack1.isEmpty()) {

            this.stack2.push(this.stack1.pop());

        }

        E oldElement = this.stack2.pop();

        while (!this.stack2.isEmpty()) {

            this.stack1.push(this.stack2.pop());

        }

        return oldElement;

    }


 }

