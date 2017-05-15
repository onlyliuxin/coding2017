package basic.stack;

/**
 * 用一个数组实现两个栈
 * 将数组的起始位置看作是第一个栈的栈底，将数组的尾部看作第二个栈的栈底，压栈时，栈顶指针分别向中间移动，直到两栈顶指针相遇，则扩容。
 * @author liuxin
 *
 */
public class TwoStackInOneArray {
	private Object[] data ;
	private int top1 ; //应该把下标对应数据
	private int top2 ;

	public TwoStackInOneArray(int size){
		data = new Object[size];
		top1 = -1;
		top2 = data.length;
	}

	public TwoStackInOneArray(){
		data = new Object[10];
		top1 = -1;
		top2 = data.length;
	}
	
	/**
	 * 向第一个栈中压入元素
	 * @param o
	 */
	public void push1(Object o){
		capacity();
		data[++top1] = o;
	}
	/**
	 * 从第一个栈中弹出元素
	 * @return
	 */
	public Object pop1(){
		if(top1==-1){
			throw  new RuntimeException("The stack is empty!");
		}
		Object o = data[top1];
		data[top1] = null;
		top1--;
		return o;
	}
	
	/**
	 * 获取第一个栈的栈顶元素
	 * @return
	 */
	public Object peek1(){
		if(top1==-1){
			throw  new RuntimeException("The stack is empty!");
		}
		return data[top1];
	}
	/*
	 * 向第二个栈压入元素
	 */
	public void push2(Object o){
		capacity();
		data[--top2] = o;
	}
	/**
	 * 从第二个栈弹出元素
	 * @return
	 */
	public Object pop2(){
		if(top2==data.length){
			throw  new RuntimeException("The stack is empty!");
		}
		Object o = data[top2];
		data[top2] = null;
		top1++;
		return o;
	}

	/**
	 * 获取第二个栈的栈顶元素
	 * @return
	 */
	public Object peek2(){
		if(top2==data.length){
			throw  new RuntimeException("The stack is empty!");
		}
		return data[top2];
	}

	public Object[] stack1ToArray(){
		Object[] result = new Object[top1+1];
		System.arraycopy(data,0,result,0,top1+1);
		return result;
	}
	public Object[] stack2ToArray(){
		int stack2Size = data.length-top2;
		Object[] result = new Object[stack2Size];
		for(int i=data.length-1,j=0; i>=top2; i--,j++){
			result[j] = data[i];
		}
		return result;
	}

	//扩容函数
	private void capacity(){
		if(top2-top1<=1){
			int size = data.length*2+1;
			Object[] newData = new Object[size];
			System.arraycopy(data,0,newData,0,top1+1);
			int stack2Size = data.length-top2;
			System.arraycopy(data,top2,newData,newData.length-stack2Size,stack2Size);
			top2 = newData.length-stack2Size;
			data = newData;
		}
	}
}
