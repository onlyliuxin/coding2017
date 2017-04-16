package main.week01.data_structure;

public class Queue {
	
	private LinkedList elementData;
	private int size = 0;
	
	public void enQueue(Object o){		
		elementData.add(size++,o);
	}
	
	public Object deQueue(){
		return elementData.remove(size---1);
	}
	
	public boolean isEmpty(){
		return size == 0;
	}
	
	public int size(){
		return size;
	}
}
