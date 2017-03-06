package djj;

import java.util.*;

//队列
public class Queue {
	private LinkedList elementList;

	//入队
	public void enQueue(Object o){
		elementList.add(o);
	}

	//出队
	public Object deQueue(){
		return elementList.remove(1);
	}
	
	public boolean isEmpty(){
		return elementList.size==0?true:false;

	}
	
	public int size(){
		return elementList.size;
	}
}
