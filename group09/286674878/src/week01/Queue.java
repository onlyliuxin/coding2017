package week01;

import java.util.Arrays;
//以后有时间要改成循环队列  
public class Queue {
	private ArrayList elementData = new ArrayList();
	private int front;
	private int rear;
	
	public void enQueue(Object o){	
		elementData.add(o);
	}
	
	public Object deQueue(){
		
		return null;
	}
	
	public boolean isEmpty(){
		if(elementData.size()>0){
			return false;
		}else return true;
	}
	
	public int size(){
		return elementData.size();
	}

}

