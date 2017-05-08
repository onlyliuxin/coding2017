package cn.xl.basic.stack;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * Stack是一个后进先出（last in first out，LIFO）的堆栈，
 * 在Vector类的基础上扩展5个方法而来
 * @author XIAOLONG
 *
 */
public class MyStack {

	private int elementCount;

	private Object[] elementData = new Object[5];

	/**
	 * 无参构造方法，创建一个空栈
	 * 
	 */
	public MyStack(){

	}


	/**
	 * 元素入栈
	 * @param item
	 * @return 入栈元素
	 */
	public synchronized Object push(Object item){

		ensureCapacity(elementCount+1);
		elementData[elementCount] = item;
		elementCount ++;
		return item;
	}

	/**
	 * 将栈顶元素移除，并返回该元素
	 * @return  栈顶元素
	 */
	public synchronized Object pop(){
		Object    obj;

		obj = peek();
		elementCount --;
		elementData[elementCount] = null;

		return obj;
	}

	/**
	 * 查看栈顶元素
	 * 
	 * @return 栈顶元素
	 * @throws 如果栈为空 ， 抛出 EmptyStackException异常  .
	 */
	public synchronized Object peek(){
		int len = elementCount;

		if(len == 0)
			throw new EmptyStackException();

		return  elementData[len - 1];

	}

	/**
	 * 检查栈是否为空
	 * 
	 * @return True or false
	 */
	public boolean isEmpty(){

		return elementCount == 0;
	}

	/**
	 * 查询占栈是否存在某元素
	 * @param  查询元素
	 * @return 如果元素存在返回元素所在位置（栈顶元素位置为1）；
	 *         如果该元素在栈中有重复，则返回距离栈顶最近的元素位置；
	 *         如果该元素在栈中不存在，则返回 -1 。
	 */
	public synchronized int search(Object o){

		if(o == null){
			for(int i = elementCount -1;i >= 0; i--){
				if(elementData[i] == null){
					return elementCount - i;
				}
			}
		}else{
			for(int i = elementCount -1;i >= 0; i-- ){
				if(o.equals(elementData[i])){
					return elementCount - i;
				}
			}
		}

		return -1;
	}

	/**
	 * 扩展容量，增加自身容量的一倍
	 * @param 当前栈所需最小容量size
	 */
	private void ensureCapacity(int minCapacity){
		int oldCapacity = elementData.length;
		if(minCapacity > oldCapacity){
			int newCapacity = oldCapacity << 1;
			elementData = Arrays.copyOf(elementData, newCapacity);
		}
	}

	public static void main(String[] args){
		
		
		
	}

}
