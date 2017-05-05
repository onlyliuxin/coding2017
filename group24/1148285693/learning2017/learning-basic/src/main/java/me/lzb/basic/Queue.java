package me.lzb.basic;

/**
 * 先进先出
 * Created by LZB on 2017/3/11.
 */
public class Queue {
	LinkedList elementData = new LinkedList();

	public void enQueue(Object o){
		elementData.add(o);
	}

	public Object deQueue() throws IndexOutOfBoundsException{
		if(isEmpty()){
			throw new IndexOutOfBoundsException("index boom");
		}
		return elementData.remove(elementData.size() - 1);
	}

	public boolean isEmpty(){
		if(elementData.size() <= 0){
			return true;
		}
		return false;
	}

	public int size(){
		return elementData.size();
	}
}
