package com.aaront.exercise.basic;

import java.util.Arrays;

/**
 * 设计一个栈，支持栈的push和pop操作，以及第三种操作findMin, 它返回该数据结构中的最小元素
 * finMin操作最坏的情形下时间复杂度应该是O(1) ， 简单来讲，操作一次就可以得到最小值
 */
public class QuickMinStack {

    private static final int DEFAULT_LENGTH = 20;
    private Node[] nodes = new Node[DEFAULT_LENGTH];

    private int size = 0;

    public void push(int data) {
        _ensureCapacityEnough();
        if (size == 0) {
            Node node = new Node(data, data);
            nodes[size++] = node;
        } else {
            int min = nodes[size - 1].min;
            Node node = new Node(data, data > min ? min : data);
            nodes[size++] = node;
        }
    }

    public int pop() {
        if (size <= 0) throw new RuntimeException("栈为空");
        return nodes[--size].data;
    }

    public int findMin() {
        return nodes[size - 1].min;
    }

    public int size() {
        return this.size;
    }

    private void _ensureCapacityEnough() {
        if (size >= nodes.length) {
            nodes = Arrays.copyOf(nodes, DEFAULT_LENGTH * 2);
        }
    }
}

class Node {
    int data;
    int min;

    public Node(int data, int min) {
        this.data = data;
        this.min = min;
    }
}