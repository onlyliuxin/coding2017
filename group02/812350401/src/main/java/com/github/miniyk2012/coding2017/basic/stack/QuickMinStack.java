package com.github.miniyk2012.coding2017.basic.stack;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 设计一个栈，支持栈的push和pop操作，以及第三种操作findMin, 它返回改数据结构中的最小元素
 * finMin操作最坏的情形下时间复杂度应该是O(1) ， 简单来讲，操作一次就可以得到最小值
 * @author liuxin
 * 我用的有序链表实现的最小数栈，感觉不是好方法
 */
public class QuickMinStack<E extends Comparable<E>> {
	List<E> sortedList = new LinkedList<E>();  // 从小到大排列
	Stack<E> stack = new Stack();

	public void push(E data){
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
	public E pop(){
		E result = stack.pop();
		sortedList.remove(result);
		return result;
	}

    /**
     * 如果为空，抛异常；否则给出栈中最小数
     * @return
     */
	public E findMin(){
		return sortedList.get(0);
	}
}
