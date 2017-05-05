package basic.dataStructure.stack;

/**
 * 设计一个栈，支持栈的push和pop操作，以及第三种操作findMin, 它返回改数据结构中的最小元素
 * finMin操作最坏的情形下时间复杂度应该是O(1) ， 简单来讲，操作一次就可以得到最小值
 * @author liuxin
 *
 */
public class QuickMinStack {

	Stack s = null;

	private int min = -1;

	public QuickMinStack(){
		s = new Stack();
	}

	public void push(int data){
		if(s.isEmpty()){
			this.min = data;
		}else this.min = data < min ? data : min;

		s.push(data);
	}
	public int pop(){
		return (Integer)s.pop();
	}
	public int findMin(){
		return this.min;
	}
}
