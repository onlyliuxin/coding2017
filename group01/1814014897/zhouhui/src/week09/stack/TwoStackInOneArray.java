package week09.stack;

import java.util.Arrays;

/**
 * 用一个数组实现两个栈 将数组的起始位置看作是第一个栈的栈底，将数组的尾部看作第二个栈的栈底，压栈时，栈顶指针分别向中间移动，直到两栈顶指针相遇，则扩容。
 * 
 * @author Hui Zhou
 *
 */
public class TwoStackInOneArray {
	Object[] data = new Object[10];
	int i = 0;
	int j = data.length - 1;

	/**
	 * 向第一个栈中压入元素
	 * 
	 * @param o
	 */
	public void push1(Object o) {
		if (i < j) {
			data[i++] = o;
		} else {
			addCapacity();
			data[i++] = o;
		}
	}

	private void addCapacity() {
		int oldCapacity = data.length;
		int newCapacity = oldCapacity + (oldCapacity >> 2);
		Object[] tempPre = Arrays.copyOfRange(data, 0, i);
		Object[] tempPost = Arrays.copyOfRange(data, j + 1, data.length);
		data = Arrays.copyOf(tempPre, newCapacity);
		System.arraycopy(tempPost, 0, data, data.length - tempPost.length, tempPost.length);
		j = data.length - tempPost.length - 1;
	}

	/**
	 * 从第一个栈中弹出元素
	 * 
	 * @return
	 */
	public Object pop1() {
		if (i <= 0) {
			throw new RuntimeException("stack1 is empty.");
		}
		Object pop1Value = data[--i];
		data[i] = null;
		return pop1Value;
	}

	/**
	 * 获取第一个栈的栈顶元素
	 * 
	 * @return
	 */

	public Object peek1() {
		return data[i - 1];
	}

	/*
	 * 向第二个栈压入元素
	 */
	public void push2(Object o) {
		if (j > i) {
			data[j--] = o;
		} else {
			addCapacity();
			data[j--] = o;
		}
	}

	/**
	 * 从第二个栈弹出元素
	 * 
	 * @return
	 */
	public Object pop2() {
		if (j >= data.length - 1) {
			throw new RuntimeException("stack2 is empty.");
		}
		Object pop2Value = data[++j];
		data[j] = null;
		return pop2Value;
	}

	/**
	 * 获取第二个栈的栈顶元素
	 * 
	 * @return
	 */

	public Object peek2() {
		return data[j + 1];
	}

}
