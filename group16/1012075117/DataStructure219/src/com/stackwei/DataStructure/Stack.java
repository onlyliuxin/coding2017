package com.stackwei.DataStructure;

/**
 * 
 * @author stackwei -2017.2.25
 *
 */
public class Stack {

	private ArrayList al = new ArrayList();
	
	/**
	 * 进栈
	 * @param item
	 */
	public void push(Object item) {
		al.add(item);
	}
	
	/**
	 * 出栈
	 * @return
	 */
	public Object pop() {
		return al.remove(al.getFlag());
	}
	
	/**
	 * 获取栈顶元素
	 * @return
	 */
	public Object peek() {
		return al.get(al.getFlag());
	}
	
	/**
	 * 栈是否为空
	 * @return
	 */
	public boolean isEmpty() {
		if (al.getFlag() >= 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * 测试用例
	 * @param args
	 */
	public static void main(String[] args) {
		Stack s = new Stack();
		s.push(98);
		s.push(99);
		s.pop();
		System.out.println(s.peek());
	}

}
