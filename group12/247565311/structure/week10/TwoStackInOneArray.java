package structure.week10;

/**
 * 用一个数组实现两个栈
 * 将数组的起始位置看作是第一个栈的栈底，将数组的尾部看作第二个栈的栈底，压栈时，栈顶指针分别向中间移动，直到两栈顶指针相遇，则扩容。
 * @author liuxin
 *
 */
public class TwoStackInOneArray {
	Object[] data = new Object[10];
	int index1 = -1,index2 = data.length;
	/**
	 * 向第一个栈中压入元素
	 * @param o
	 */
	public void push1(Object o){
		if(index2-index1<=1)doubleSpace();
		index1 += 1;
		data[index1] = o;
	}
	/**
	 * 从第一个栈中弹出元素
	 * @return
	 */
	public Object pop1(){
		if(index1<0)return null;
		Object res = data[index1];
		index1 -= 1;
		return res;
	}
	
	/**
	 * 获取第一个栈的栈顶元素
	 * @return
	 */
	
	public Object peek1(){
		if(index1<0) return null;
		return data[index1];
	}
	/*
	 * 向第二个栈压入元素
	 */
	public void push2(Object o){
		if(index2-index1<=1)doubleSpace();
		index2 -=1;
		data[index2] = o;
	}
	/**
	 * 从第二个栈弹出元素
	 * @return
	 */
	public Object pop2(){
		if(index2==data.length) return null;
		Object res = data[index2];
		index2 += 1;
		return res;
	}
	/**
	 * 获取第二个栈的栈顶元素
	 * @return
	 */
	public Object peek2(){
		if(index2==data.length) return null;
		return data[index2];
	}
	private void doubleSpace(){
		Object []newData = new Object[data.length*2];
		for(int i=0;i<=index1;i++){
			newData[i] = data[i];
		}
		for(int i=data.length-1;i>=index2;i--){
			newData[data.length+i] = data[i];
		}
		index2 += data.length;
		data=newData;
	}
}
