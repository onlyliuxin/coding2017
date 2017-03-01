package cn.net.pikachu.basic;

import java.util.NoSuchElementException;

public class Queue {
	private LinkedList list = new LinkedList();
	public void enQueue(Object o){
		list.addLast(o);
	}
	
	public Object deQueue(){
		if (isEmpty()){
			throw new NoSuchElementException();
		}
		return list.removeFirst();
	}
	
	public boolean isEmpty(){
		return list.size()==0;
	}
	
	public int size(){
		return list.size();
	}

	@Override
	public String toString() {
		return list.toString();
	}
}
