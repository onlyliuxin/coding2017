package com.github.miniyk2012.coding2017.basic.stack;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 设计一个栈，支持栈的push和pop操作，以及第三种操作findMin, 它返回改数据结构中的最小元素
 * finMin操作最坏的情形下时间复杂度应该是O(1) ， 简单来讲，操作一次就可以得到最小值
 * @author liuxin
 */
public class QuickMinStack<E extends Comparable<E>> {
	List<E> sortedList = new LinkedList<>();  // 从小到大排列,对应于V1
	Stack<E> stack = new Stack();
	Stack<E> minStack = new Stack<>();  // 更好的方法，保存了曾经的最小数的入栈顺序

	public void pushV1(E data){
		stack.push(data);
		for (int i=0; i<sortedList.size(); i++) {
			if (data.compareTo(sortedList.get(i)) > 0) {
			    i++;
            } else {
			    sortedList.add(i, data);
			    return;
            }
		}
        sortedList.add(data);
	}
	public E popV1(){
		E result = stack.pop();
		sortedList.remove(result);
		return result;
	}

    /**
     * 如果为空，抛异常；否则给出栈中最小数
     * @return
     */
	public E findMinV1(){
		return sortedList.get(0);
	}

    /**
     * 如果为空，抛异常；否则给出栈中最小数
     * @return
     */
    public E findMin(){
        return minStack.peek();
    }

    /**
     * 入栈时，若data小于等于minStack的栈顶元素，则data同时入栈stack和minStack
     * 否则，只入栈stack
     * @param data
     */
    public void push(E data) {
	    if (minStack.isEmpty()) {
	        minStack.push(data);
	        stack.push(data);
	        return;
        }

        if (data.compareTo(minStack.peek()) <= 0) {
	        minStack.push(data);
        }
        stack.push(data);
    }

    /**
     * 若stack的栈顶元素与minStack的栈顶元素相同，则minStack也要pop
     * 若为空抛异常
     * @return
     */
    public E pop(){
        E result = stack.pop();
        if (result.compareTo(minStack.peek()) == 0) {
            minStack.pop();
        }
        return result;
    }
}
