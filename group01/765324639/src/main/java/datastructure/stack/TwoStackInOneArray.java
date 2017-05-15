package datastructure.stack;

import java.util.Arrays;

/**
 * 用一个数组实现两个栈
 * 将数组的起始位置看作是第一个栈的栈底，将数组的尾部看作第二个栈的栈底，压栈时，栈顶指针分别向中间移动，直到两栈顶指针相遇，则扩容。
 * @author liuxin
 *
 */
public class TwoStackInOneArray {
	Object[] data = new Object[10];
	
	private int index1 = 0;
	private int index2 = data.length - 1;
	
	/**
	 * 向第一个栈中压入元素
	 * @param o
	 */
	public void push1(Object o){
		ensureCapacity();
		data[index1++] = o;
	}
	
	private void ensureCapacity() {
	    if (isFull()) {
	        grow();
	    }
	}
	
	private boolean isFull() {
	    return index1 + 1 == index2;
	}
	
	private void grow() {
	    int stack2Length = data.length - 1 - index2; 
	    data = Arrays.copyOf(data, data.length * 2);
	    int oldIndex2 = index2;
	    index2 = data.length - stack2Length - 1;
	    System.arraycopy(data, oldIndex2 + 1, data, index2 + 1, stack2Length);
	}
	
	/**
	 * 从第一个栈中弹出元素
	 * @return
	 */
	public Object pop1(){
		return data[--index1];
	}
	
	/**
	 * 获取第一个栈的栈顶元素
	 * @return
	 */
	
	public Object peek1(){
		return data[index1 - 1];
	}
	/**
	 * 向第二个栈压入元素
	 */
	public void push2(Object o){
	    ensureCapacity();
        data[index2--] = o;
	}
	/**
	 * 从第二个栈弹出元素
	 * @return
	 */
	public Object pop2(){
		return data[++index2];
	}
	/**
	 * 获取第二个栈的栈顶元素
	 * @return
	 */
	
	public Object peek2(){
		return data[index2 + 1];
	}
	
}
