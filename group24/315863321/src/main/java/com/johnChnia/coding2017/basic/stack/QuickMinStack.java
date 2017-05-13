package com.johnChnia.coding2017.basic.stack;

/**
 * Created by john on 2017/5/7.
 * 设计一个栈，支持栈的push和pop操作，以及第三种操作findMin, 它返回改数据结构中的最小元素
 * finMin操作最坏的情形下时间复杂度应该是O(1) ， 简单来讲，操作一次就可以得到最小值
 * 参考：http://www.programcreek.com/2014/02/leetcode-min-stack-java/
 *
 * @author john
 */


public class QuickMinStack {

    private static class Elem {
        int value;
        int min;
        Elem next;

        Elem(int value, int min) {
            this.value = value;
            this.min = min;
        }
    }

    private Elem top;


    public QuickMinStack() {

    }


    public void push(int data) {
        if (top == null) {
            Elem e = new Elem(data, data);
            top = e;
        } else {
            Elem temp = new Elem(data, Math.min(data, top.min));
            temp.next = top;
            top = temp;
        }
    }

    public int pop() {
        if (top == null) {
            return -1;
        } else {
            Elem temp = top.next;
            top.next = null;
            top = temp;
            return top.value;
        }
    }

    public int findMin() {
        if (top == null) {
            return -1;
        }
        return top.min;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        while (top != null) {
            sb.append(top.value);
            sb.append(",");
            top = top.next;
        }
        return sb.toString();
    }
}
