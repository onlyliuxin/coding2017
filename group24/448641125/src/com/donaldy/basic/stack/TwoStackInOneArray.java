package com.donaldy.basic.stack;

/**
 * 用一个数组实现两个栈
 * 将数组的起始位置看作是第一个栈的栈底，将数组的尾部看作第二个栈的栈底，
 * 压栈时，栈顶指针分别向中间移动，直到两栈顶指针相遇，则扩容。
 * @author liuxin
 *
 */
public class TwoStackInOneArray {

	Object[] data = new Object[10];

	/**
	 * 0, 10用来存储指针，
	 * 用于判断 栈是否为空
	 */
	int pointer1 = 0;
	int pointer2= 9;
	
	/**
	 * 向第一个栈中压入元素
	 * @param o
	 */
	public void push1(Object o){

		ensureCapacity();

		this.data[++ this.pointer1] = o;

	}

	/**
	 * 扩容后，pointer1指针不变，pointer2指针要改变
	 * 注意复制
	 */
	private void ensureCapacity() {

		if (this.pointer1 + 1 ==  this.pointer2 || this.pointer1 == this.pointer2 + 1) {

			int newCapacity = this.data.length * 2 + 1;

			Object[] newDataArray = new Object[newCapacity];

			System.arraycopy(this.data, 1, newDataArray, 1, this.pointer1);

			int pointer2Len = this.data.length - 1 - this.pointer2;

			System.arraycopy(this.data, this.pointer2, newDataArray,
				newCapacity - pointer2Len - 1, pointer2Len);

			this.pointer2 = newCapacity - pointer2Len - 1;

			this.data = newDataArray;
		}

	}
	/**
	 * 从第一个栈中弹出元素
	 * @return
	 */
	public Object pop1() {

		if (this.pointer1 <= 0 || this.pointer1 >= this.data.length) {
			throw new IndexOutOfBoundsException("size : " + this.pointer1);
		}

		return this.data[this.pointer1 --];
	}
	
	/**
	 * 获取第一个栈的栈顶元素
	 * @return
	 */
	
	public Object peek1(){

		if (this.pointer1 <= 0 || this.pointer1 >= this.data.length) {
			throw new IndexOutOfBoundsException("size : " + this.pointer1);
		}

		return this.data[this.pointer1];
	}




	/**
	 * 向第二个栈压入元素
	 */
	public void push2(Object o){

		ensureCapacity();
		System.out.println("push : " + o);
		this.data[-- this.pointer2] = o;
	}
	/**
	 * 从第二个栈弹出元素
	 * @return
	 */
	public Object pop2(){

		if (this.pointer2 >= this.data.length || this.pointer2 <= 0) {
			throw new IndexOutOfBoundsException("size : " + this.pointer2);
		}

		return this.data[this.pointer2 ++];
	}
	/**
	 * 获取第二个栈的栈顶元素
	 * @return
	 */
	
	public Object peek2(){
		if (this.pointer2 >= this.data.length || this.pointer2 <= 0) {
			throw new IndexOutOfBoundsException("size : " + this.pointer2);
		}

		return this.data[this.pointer2];
	}
	
}
