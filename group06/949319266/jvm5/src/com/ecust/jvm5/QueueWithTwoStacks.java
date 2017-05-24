package com.ecust.jvm5;

import java.util.Stack;



/**

 * 用两个栈来实现一个队列

 * @author liuxin

 *

 * @param <E>

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

        if (stack1.isEmpty()) {

            return null;

        }

        while (!stack1.isEmpty()) {

            stack2.push(stack1.pop());

        }

        E e = stack2.pop();

        while (!stack2.isEmpty()) {

            stack1.push(stack2.pop());

        }

        return e;

    }



    public static void main(String[] args) {

        QueueWithTwoStacks queue = new QueueWithTwoStacks();

        queue.enQueue(1);

        queue.enQueue(2);

        queue.enQueue(3);

        queue.enQueue(4);

        queue.enQueue(5);

        queue.enQueue(6);

        System.out.println(queue.deQueue());

        System.out.println(queue.deQueue());

        System.out.println(queue.deQueue());

        System.out.println(queue.deQueue());System.out.println(queue.deQueue());System.out.println(queue.deQueue());

        System.out.println(queue.deQueue());

        queue.enQueue(7);

        queue.enQueue(8);

        queue.enQueue(9);

        queue.enQueue(0);

        queue.enQueue(1);

        System.out.println(queue.deQueue());

        System.out.println(queue.deQueue());

        System.out.println(queue.deQueue());

        System.out.println(queue.deQueue());

        System.out.println(queue.deQueue());

        System.out.println(queue.deQueue());





    }



 }

