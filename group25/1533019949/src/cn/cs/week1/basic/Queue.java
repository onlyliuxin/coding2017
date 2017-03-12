package cn.cs.week1.basic;

import java.util.Arrays;

public class Queue {
	private Object[] elements;//保存队列元素
	private final int DEFAULT_CAPACITY = 16;//队列默认容量
	private int capacity;//当前队列容量
	private int size;//当前队列长度

	public Queue(){
		capacity = DEFAULT_CAPACITY;
		elements = new Object[capacity];
	}

	private void ensureCapacity(int miniCap){
		if(miniCap > capacity){
			while(miniCap > this.capacity){
				capacity <<= 1;//2倍扩容
			}
			elements = Arrays.copyOf(elements,this.capacity);
		}
	}

	public void enQueue(Object o){
		ensureCapacity(size + 1);
		elements[size] = o;
		size++;
	}
	
	public Object deQueue(){
		Object o = elements[size - 1];
		size --;
		return o;
	}
	
	public boolean isEmpty(){
		return size == 0;
	}
	
	public int size(){
		return size;
	}
}
