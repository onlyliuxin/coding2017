package week1.collections;

public class Queue {
	
	private LinkedList list = new LinkedList();
	
	public boolean enQueue(Object o){
		list.add(o);
		return true;
	}
	
	public Object deQueue(){
		Object o = list.removeFirst();
		return o;
	}
	
	public boolean isEmpty(){
		return list.size()==0;
	}
	
	public int size(){
		return list.size();
	}
}
