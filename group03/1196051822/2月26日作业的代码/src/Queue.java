package com.byhieg.coding2017;

public class Queue {

    private LinkedList list = new LinkedList();
	public void enQueue(Object o){
        list.addLast(o);
	}
	
	public Object deQueue() {
        Object value = list.get(0);
        list.removeFirst();
        return value;
    }
	
	public boolean isEmpty(){
		return size() == 0;
	}
	
	public int size(){
		return list.size();
	}
}
