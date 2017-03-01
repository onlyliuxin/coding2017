package week01.BasicDataStructure;

public class Queue {
	
	private LinkedList linkedList = new LinkedList();
	private int size = 0;
	
	public void enQueue(Object o){		
		linkedList.add(o);
		size++;
	}
	
	public Object deQueue(){
		size--;
		return linkedList.removeFirst();
	}
	
	public boolean isEmpty(){
		return linkedList.size() == 0;
	}
	
	public int size(){
		return size;
	}
}
