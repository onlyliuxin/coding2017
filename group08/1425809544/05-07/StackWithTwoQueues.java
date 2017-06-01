package com.zhuoyue.scheduleplan.domain;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author xyy
 * @create 2017-05-25 16:17
 **/
public class StackWithTwoQueues {


    //声明两个队列
    Queue<Integer> queue1 = new ArrayDeque<>();
    Queue<Integer> queue2 = new ArrayDeque<>();

    public void push(int data) {

        if (isEmpty()) {
            queue1.add(data);
            return;
        }
        if (queue1.isEmpty()) {
            queue2.add(data);
            return;
        }
        if (queue2.isEmpty()) {
            queue1.add(data);
            return;
        }
    }

    public int pop() {

        if (isEmpty()) {
            throw new RuntimeException("栈为空");
        }
        if (queue1.isEmpty()) {
            while (queue2.size() > 1) {
                queue1.add(queue2.poll());
            }

            return queue2.poll();
        }
        if (queue2.isEmpty()) {
            while (queue1.size() > 1) {
                queue2.add(queue1.poll());
            }
        }
        return queue1.poll();
    }


    public boolean isEmpty() {
        return queue1.isEmpty() && queue2.isEmpty();
    }


    public static void main(String[] args) {
        StackWithTwoQueues stack = new StackWithTwoQueues();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }

    }

}
