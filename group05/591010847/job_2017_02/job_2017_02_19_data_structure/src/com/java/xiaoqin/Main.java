package com.java.xiaoqin;

import com.java.xiaoqin.impl.BinaryTreeNode;

import java.util.Stack;

/**
 * Created by xiaoqin on 17-2-26.
 */
public class Main {
    public static void main(String[] args) {
//        test();
        testMe();
    }

    private static void testMe() {
        BinaryTreeNode<Integer> node = new BinaryTreeNode<>();
        node.insert(5);
        node.insert(2);
        node.insert(7);
        node.insert(1);
        node.insert(6);
        node.insert(4);
        node.insert(8);
        System.out.println(node.toString());
    }

    private static void test() {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 0; i++) {
            stack.push(i);
        }
        System.out.println(stack.peek());
        System.out.println(stack.peek());
    }
}
