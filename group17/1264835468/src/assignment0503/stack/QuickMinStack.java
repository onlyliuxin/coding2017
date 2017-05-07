package assignment0503.stack;

import java.util.Stack;

/**
 * 设计一个栈，支持栈的push和pop操作，以及第三种操作findMin, 它返回改数据结构中的最小元素
 * finMin操作最坏的情形下时间复杂度应该是O(1) ， 简单来讲，操作一次就可以得到最小值
 *
 * @author liuxin
 */
public class QuickMinStack {
    Stack<Integer> stack = new Stack<>();
    Stack<Integer> minStack = new Stack<>();

    public void push(int data) {
        stack.push(data);
        if (minStack.empty()) {
            minStack.push(data);
        } else if (data < minStack.peek()) {
            minStack.push(data);
        } else {
            minStack.push(minStack.peek());
        }

    }

    public int pop() {
        minStack.pop();
        return stack.pop();
    }

    public int findMin() {
        return minStack.peek();
    }
}
