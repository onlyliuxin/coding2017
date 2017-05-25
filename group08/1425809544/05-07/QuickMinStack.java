package com.zhuoyue.scheduleplan.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 返回最小值
 *
 * @author xyy
 * @create 2017-05-08 10:30
 **/
public class QuickMinStack {


    private List<Integer> min = new ArrayList<>();
    private Stack<Integer> stack = new Stack<>();

    public void push(int i) {
        stack.push(i);

        if (min.size() == 0) {
            min.add(i);
        } else {
            int temp = min.get(min.size() - 1);
            if (temp >= i) {
                min.add(i);
            } else {
                min.add(min.size() - 1, i);
                min.add(temp);
            }
        }
    }

    public int pop() {
        if (stack.isEmpty()) {
            return -1;
        }
        int temp = stack.pop();
        return min.remove(temp);
    }

    public int findMin() {
        if (stack.isEmpty()) {
            return -1;
        }
        return min.get(min.size() - 1);
    }


    public static void main(String[] args) {
        QuickMinStack quickMinStack = new QuickMinStack();

        quickMinStack.push(2);
        quickMinStack.push(1);
        quickMinStack.push(4);
        quickMinStack.push(3);
        quickMinStack.push(4);

        System.out.println(quickMinStack.findMin());

    }


}
