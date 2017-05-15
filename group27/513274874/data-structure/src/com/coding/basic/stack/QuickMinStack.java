package com.coding.basic.stack;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 设计一个栈，支持栈的push和pop操作，以及第三种操作findMin, 它返回改数据结构中的最小元素
 * finMin操作最坏的情形下时间复杂度应该是O(1) ， 简单来讲，操作一次就可以得到最小值
 * @author liuxin
 *
 */
public class QuickMinStack {

	//存储栈中元素，升序排列，minAscList.get(0)即能取出
	private List<Integer> minAscList = new ArrayList<>();

	private Stack stack;

	public void push(int data){
		stack.push(data);
		minAscList.add(data);
		minAscList.sort(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}
		});
	}
	public int pop(){

		int val = (Integer)stack.pop();
		minAscList.remove(new Integer(val));

		return val;
	}
	public int findMin(){
		return minAscList.get(0);
	}


}