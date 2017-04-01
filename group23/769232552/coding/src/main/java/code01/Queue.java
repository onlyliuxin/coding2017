package code01;

public class Queue {

	private LinkedList linkedList = new LinkedList();

	public void enQueue(Object o){
		linkedList.addFirst(o);
	}
	
	public Object deQueue(){
		Object result = linkedList.removeLast();
		return result;
	}
	
	public boolean isEmpty(){
		return linkedList.size() == 0;
	}
	
	public int size(){
		return linkedList.size();
	}

}
