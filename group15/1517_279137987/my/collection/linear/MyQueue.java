package my.collection.linear;

public class MyQueue {
	MyLinkedList queue = new MyLinkedList();
	
	public void enQueue(Object obj){
		queue.add(obj);
	}
	
	public Object deQueue(){
		return queue.removeFirst();
	}
	
	public boolean isEmpty(){
		if(queue.size() == 0){
			return true;
		}else{
			return false;
		}
	}
	
	public int size(){
		return queue.size();
	}
	
}
