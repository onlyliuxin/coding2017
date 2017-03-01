package hw1;

public class QueueImpl {
	LinkedList queueList = new LinkedList();
	
	public void enQueue(Object o){
		queueList.add(o);
	}
	
	public Object deQueue(){
		return queueList.removeFirst();
	}
	
	public boolean isEmpty(){
		return queueList.size() == 0;
	}
	
	public int size(){
		return queueList.size();
	}
}
