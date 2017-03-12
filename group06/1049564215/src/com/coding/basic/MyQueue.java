
/**
 * @author CCD
 *
 */

import java.util.*;

public class MyQueue {
	
	private static final int  DEFAULT_SIZE = 10;
	
	private Object[] elementData;
	private int head;
	private int tail;
	public MyQueue(){
		this(DEFAULT_SIZE);
	}
	public MyQueue(int size){
		this.elementData = new Object[size];
		this.head = 0;
		this.tail = 0;
	}
	
	public void enQueue(Object o){	
		if((tail+1)%elementData.length == head){
		}
		else{
			elementData[tail] = o;
			tail = (tail+1)%elementData.length;
		}
	}
	
	public Object deQueue(){
		if(head == tail){
			return null;
		}
		else{
			Object o = elementData[head];
		    head = (head+1)% elementData.length;
		    return o ;
		}
	}
	
	public boolean isEmpty(){
		return head == tail ;
	}
	
	public int size(){
		return  (tail-head)&(elementData.length -1);
	}
}
