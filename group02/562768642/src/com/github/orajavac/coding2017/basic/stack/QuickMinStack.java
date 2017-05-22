package com.github.orajavac.coding2017.basic.stack;


/**
 * 设计一个栈，支持栈的push和pop操作，以及第三种操作findMin, 它返回改数据结构中的最小元素
 * finMin操作最坏的情形下时间复杂度应该是O(1) ， 简单来讲，操作一次就可以得到最小值
 * @author liuxin
 *
 */
public class QuickMinStack {
	
	private Stack s1 = new Stack();
	
	private Stack s2 = new Stack();
	
	private int min = 0;
	
	public void push(int data){
		if(s1.length() == 0){
			s1.push(data);
			s2.push(data);
			min = data;
		}else{
			if (data <= min){
				s2.push(data);
				min = data;
			}
			s1.push(data);
		}
	}
	
	public int pop(){
		Integer result = Integer.parseInt(s1.pop().toString());
		if (result == min){
			min = Integer.parseInt(s2.pop().toString());
		}
		return result;
	}
	
	public int findMin(){
		Integer min_ = Integer.parseInt(s2.pop().toString());
		s2.push(min_);
		return min_;
	}
	
	//测试使用，观察栈里元素
    public void toStrings(){
    	System.out.println("s1 = "+s1.positiveToString());
    	System.out.println("s2 = "+s2.positiveToString());
    }
}
