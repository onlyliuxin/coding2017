package task1.basic;

import java.util.LinkedList;

public class Queue {

	private LinkedList q=new LinkedList();
	
	public void enQueue(Object o){
		
		q.addLast(o);
	}
	
	public Object deQueue(){
		return q.removeFirst();
		
	}
	
	public int size(){
		
		return q.size();
	}
	
	public static void main(String[] args){
		Queue testQ= new Queue();
		
		testQ.enQueue(11);
		testQ.enQueue(12);
		
        testQ.enQueue(13);
        
        System.out.println(testQ.size());
        
        Object s1=testQ.deQueue();
        System.out.println(s1);
        
        Object s2=testQ.deQueue();
        System.out.println(s2);
        
        
        
		
	}
	
}
