package stack;

import java.util.Stack;

/**
 * 设计一个栈，支持栈的push和pop操作，以及第三种操作findMin, 它返回改数据结构中的最小元素
 * finMin操作最坏的情形下时间复杂度应该是O(1) ， 简单来讲，操作一次就可以得到最小值
 * @author liuxin
 *
 */
public class QuickMinStack {
	private Stack<Integer> stack;
	private Integer minData;
	
	public QuickMinStack(){
		stack = new Stack<Integer>();
	}
	
	public void push(Integer data){
		stack.push(data);
		if(minData == null){
			minData = data;
		}else{
			minData = min(minData, data);
		}
	}
	private int min(int minData, int data) {
		return data < minData ? data:minData;
	}

	public int pop(){
		return stack.pop();
	}
	public int findMin(){
		return minData;
	}
}
