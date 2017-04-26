
package com.coding.basic.stack;

import com.coding.basic.List;
import com.coding.basic.array.ArrayList;

/**
 * author zhougd 20170306
 *
 */
public class Stack {
	private List elementData = new ArrayList();


	public Stack() {
	}

	/**
	 * 入栈
	 * @param o
	 */
	public void push(Object o){
		elementData.add(o);
	}

	/**
	 * 出栈
	 * @return
	 */
	public Object pop(){
		if(this.isEmpty()){
			throw new IndexOutOfBoundsException("stack is empty!");
		}
		Object element = elementData.get(size()-1);
		elementData.remove(size()-1);
		return element;
	}

	/**
	 * 查看栈顶元素
	 * @return Object
	 */
	public Object peek(){
		if(this.isEmpty()){
			throw new IndexOutOfBoundsException("stack is empty!");
		}
		Object element = elementData.get(size()-1);
		return element;
	}

	/**
	 * 查看栈是否为空
	 * @return boolean
	 */
	public boolean isEmpty(){

		return elementData == null || elementData.size()<=0;

	}

	/**
	 * 获取栈大小
	 * @return
	 */
	public int size(){
		return elementData.size();
	}
}