package Collection.Onehomework;

public class Queue {
	private LinkedList elementData = new LinkedList();
	private int size = 0;
	public void enQueue(Object o){
		elementData.addLast(o);
		size++;
	}
	
	public Object deQueue(){
		if(isEmpty())
			try {
				throw new IllegalAccessException();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		size--;
		return elementData.removeFirst();
	}
	
	public boolean isEmpty(){
		return size==0;
	}
	
	public int size(){
		return size;
	}
}
