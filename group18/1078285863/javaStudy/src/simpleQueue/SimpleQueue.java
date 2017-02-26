package simpleQueue;

import java.util.LinkedList;;

public class SimpleQueue {
	LinkedList<Object> queueList = new LinkedList();
	public void enQueue(Object o){	
		queueList.add(o);
	}
	
	public Object deQueue(){
		return queueList.removeFirst();
	}
	
	public boolean isEmpty(){
		return queueList.isEmpty();
	}
	
	public int size(){
		return queueList.size();
	}
}