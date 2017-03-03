package org.learning.container;

public class Queue {
	
	private org.learning.container.LinkedList linkedList = new LinkedList();
	
	
	
	public void push(Object obj){
		linkedList.add(obj);
	}
	
	public Object pop(){
		Object obj = linkedList.getFirst();
		linkedList.removeFirst();
		return obj;
	}
	public boolean isEmpty(){
		return (linkedList == null || linkedList.size() == 0) ? true:false; 
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub()
		//先进先出
		
		org.learning.container.Queue queue = new Queue();
		
		queue.push("1");
		queue.push("2");
		queue.push("3");
		print(queue.isEmpty());
		Object obj = queue.pop();
		print(obj);
		print(queue.isEmpty());
		
		Object obj2 = queue.pop();
		print(obj2);
		
		
	}
	public static void print(Object obj){
		System.out.println(""+obj);
	}

}
