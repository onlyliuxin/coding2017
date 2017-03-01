package com.coding.basic;

/**
 * 堆栈是先进后出的结构。
 */
public class Stack {
    protected int elementCount;
	private LinkedList elementData = new LinkedList();

    /**
     * 向栈顶插入元素，失败则抛出异常。同LikedList中的addFirst();
     * @param o
     */
	public void push(Object o){
        elementData.addFirst(o);
	}

    /**
     * 获取并删除栈顶元素，失败则抛出异常。同LikedList中的removeFirst();
     * @return
     */
	public Object pop(){
		return elementData.removeFirst();
	}

    /**
     * 获取但不删除栈顶元素，失败则抛出异常. 同LinkedList中的peekFirst();
     * @return
     */
	public Object peek(){
		return elementData.peekFirst();
	}
	public boolean isEmpty(){
        return size() == 0;
	}
	public int size(){
		return elementData.size();
	}
}
