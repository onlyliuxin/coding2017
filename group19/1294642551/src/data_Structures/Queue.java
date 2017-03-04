package data_Structures;

public class Queue {
	
	private LinkedList ll;
	
	Queue()
	{
		ll = new LinkedList();
	}
	
	public void enQueue(Object o){
		ll.add(o);;
	}
	
	public Object deQueue(){
		return ll.removeFirst();
	}
	
	public boolean isEmpty(){
		int size = ll.size();
		return (size == 0);
	}
	
	public int size(){
		return ll.size();
	}
}
