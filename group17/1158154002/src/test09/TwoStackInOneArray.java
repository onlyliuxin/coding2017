package test09;

/**
 * 用一个数组实现两个栈
 * 将数组的起始位置看作是第一个栈的栈底，将数组的尾部看作第二个栈的栈底，压栈时，栈顶指针分别向中间移动，直到两栈顶指针相遇，则扩容。
 * @author liuxin
 *
 */
public class TwoStackInOneArray {
	private static int SIZE=10;
	
	Object[] data = new Object[SIZE];
	
	public int first;
	
	public int end=SIZE-1;
	
	/**
	 * 向第一个栈中压入元素
	 * @param o
	 */
	public void push1(Object o){
		checkSizenAndExtend();
		data[first]=o;
		++first;
	}
	
	/**
	 * 从第一个栈中弹出元素
	 * @return
	 */
	public Object pop1(){
		if (first>0) {
			data[first]=null;
			return data[first--];	
		}
		return null;
	}
	
	/**
	 * 获取第一个栈的栈顶元素
	 * @return
	 */
	
	public Object peek1(){
		return data[first];
	}
	
	/*
	 * 向第二个栈压入元素
	 */
	public void push2(Object o){
		checkSizenAndExtend();
		data[end]=o;
		--end;
	}
	
	/**
	 * 从第二个栈弹出元素
	 * @return
	 */
	public Object pop2(){
		if (end<SIZE-1) {
			data[end]=null;
			return data[end++];
		}
		return null;
	}
	
	/**
	 * 获取第二个栈的栈顶元素
	 * @return
	 */
	public Object peek2(){
		return data[end];
	}
	
	private void checkSizenAndExtend(){
		if (checkSize()) {
			extend();
		}
	}
	
	private boolean checkSize(){
		if (first==end) {
			return true;
		}
		return false;	
	}
	
	private void extend(){
		SIZE=SIZE*2;
		Object[] newArr=new Object[SIZE];
		System.arraycopy(data, 0, newArr, 0, first);
		System.arraycopy(data, end, newArr, SIZE/2+end, SIZE/2-end);
		end=SIZE/2+end;
		data=newArr;
	}

	public void print() {
		for (int i = 0; i < SIZE; i++) {
			System.out.print(data[i]+" ");
		}
	}
}