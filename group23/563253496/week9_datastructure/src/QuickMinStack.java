//package com.coding.basic.stack;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 设计一个栈，支持栈的push和pop操作，以及第三种操作findMin, 它返回改数据结构中的最小元素
 * finMin操作最坏的情形下时间复杂度应该是O(1) ， 简单来讲，操作一次就可以得到最小值
 *
 * @author liuxin
 */
public class QuickMinStack {
    LinkedList<Integer> list = new LinkedList<Integer>();
    LinkedList<Integer> temp = new LinkedList<>();
    int min = Integer.MAX_VALUE;

    public void push(int data) {
        if (data < min) {
            min = data;
        }
        list.add(data);
    }

    public int pop() {
        int num = list.remove(list.size() - 1);
        if (num == min) {
            for (int tmp : list) {
                temp.add(tmp);
            }
        }
        Collections.sort(temp);
        min = temp.get(0);
        return list.remove(list.size() - 1);
    }

    public int findMin() {
        return min;
    }
}