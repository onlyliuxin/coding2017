public class Queue<T> {

    private LinkedList<T> element = new LinkedList<T>();



	public void enQueue(T o){
	    element.addLast(o);
	}
	
	public T deQueue(){
		return element.removeFirst();
	}
	
	public boolean isEmpty(){
		return element.isEmpty();
	}
	
	public int size(){
		return element.size();
	}
}
