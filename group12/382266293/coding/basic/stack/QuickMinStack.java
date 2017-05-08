package stack;

import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * 设计一个栈，支持栈的push和pop操作，以及第三种操作findMin, 它返回改数据结构中的最小元素
 * finMin操作最坏的情形下时间复杂度应该是O(1) ， 简单来讲，操作一次就可以得到最小值
 * @author liuxin
 *
 */
public class QuickMinStack {
	
	Stack<Integer> s1 = new Stack<>();
	private Stack<Integer> min = new Stack<>();

	
	public void push(int data){
		s1.push(data);
		if (size() == 0) {
			
			min.push(data);
		} else {
			Stack<Integer> temp = new Stack<Integer>();
			while(!min.isEmpty() && data >min.peek()) {
				temp.push(min.pop());
			}
			min.push(data);
			while(!temp.isEmpty()) {
				min.push(temp.pop());
			}
		}
	}
	
	@Override
	public String toString() {
		return "QuickMinStack [s1=" + s1 + ", min=" + min + "]";
	}

	public int pop(){
		
		checkEmpty();
		int result = s1.pop();
		Stack<Integer> temp = new Stack<Integer>();
		
		while(!min.isEmpty() && min.peek() != result) {
			temp.push(min.pop());
		}

		min.pop();
		
		while(!temp.isEmpty()) {
			min.push(temp.pop());
		}
		
		return result;
	}
	
	private void checkEmpty() {
		if (size() <= 0) {
			throw new NoSuchElementException("stack is empty");
		}
		
	}

	public int size() {
		return s1.size();
	}

	public int findMin(){
		return min.peek();
	}

}
