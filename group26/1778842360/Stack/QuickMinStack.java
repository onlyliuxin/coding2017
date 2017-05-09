package stack;

import java.util.Stack;

/**
 * 设计一个栈，支持栈的push和pop操作，以及第三种操作findMin, 它返回改数据结构中的最小元素
 * finMin操作最坏的情形下时间复杂度应该是O(1) ， 简单来讲，操作一次就可以得到最小值
 * @author liuxin
 *
 */
@SuppressWarnings("hiding")
public class QuickMinStack <Integer>{
	
	private Stack<Integer> dataStack;
	private Stack<Integer> minStack;
	public QuickMinStack()
	{
		dataStack=new Stack<Integer>();
		minStack=new Stack<Integer>();
	}
	public void push(Integer data){
		dataStack.push(data);
		if(minStack.isEmpty())
		{
			minStack.push(data);
		}else if((int)data<=findMin()){
			minStack.push(data);
		}
	}
	
	public int pop(){
		if(dataStack.isEmpty())
		{
			throw new RuntimeException("your stack is Empty");
		}
		
		int value=(int) dataStack.pop();
		//如果最小值栈中的栈顶元素与元素栈栈顶的元素相等，则pop
		if(value==findMin())
		{
			minStack.pop();
		}
		return value;
	}
	
	public int findMin(){
		
		if(minStack.isEmpty())
		{
			throw new RuntimeException("your stack is Empty");
		}
		return (int) minStack.peek(); 
	}
}
