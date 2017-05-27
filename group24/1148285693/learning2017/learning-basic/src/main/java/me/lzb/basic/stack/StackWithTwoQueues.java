package me.lzb.basic.stack;


import java.util.LinkedList;

public class StackWithTwoQueues {

    private java.util.Queue<Integer> queue1;
    private java.util.Queue<Integer> queue2;
    private boolean isOne;


    public StackWithTwoQueues() {
        queue1 = new LinkedList();
        queue2 = new LinkedList();
        isOne = true;
    }


    public boolean isEmpty() {
        return queue1.isEmpty() && queue2.isEmpty();
    }


    public int size() {
        return queue1.isEmpty() ? queue2.size() : queue1.size();
    }


    public void push(int data) {

        if (isOne) {
            queue1.add(data);
        } else {
            queue2.add(data);
        }
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException(" Stack is empty now");
        }

        int r;
        if (isOne) {

            while (queue1.size() > 1) {
                queue2.add(queue1.poll());
            }
            r = queue1.poll();
            isOne = false;
        } else {

            while (queue2.size() > 1) {
                queue1.add(queue2.poll());
            }
            r = queue2.poll();
            isOne = true;
        }

        return r;
    }


    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuffer sb = new StringBuffer();
        sb.append("[");


        int a = 1;
        if (isOne) {
            for (int i : queue1) {
                sb.append(i);
                if (a < queue1.size()) {
                    sb.append(",");
                }
                a++;
            }


        } else {
            for (int i : queue2) {
                sb.append(i);
                if (a < queue2.size()) {
                    sb.append(",");
                }
                a++;
            }
        }

        sb.append("]");
        return sb.toString();
    }
}
