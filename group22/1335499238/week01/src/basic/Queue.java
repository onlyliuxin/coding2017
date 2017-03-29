package basic;

public class Queue {

	private LinkedList linkList = new LinkedList();
	
	public void enQueue(Object o){
		linkList.add(o);
	}
	
	public Object deQueue(){
		return linkList.removeFirst();
	}
	
	public boolean isEmpty(){
		return linkList.size() == 0;
	}
	public int size(){
		return linkList.size();
	}
}
