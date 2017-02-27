package Collection;

public class Queue {
	private LinkedList linkedList;
	private int size;

	public Queue(Object o) {
		linkedList = new LinkedList(o);
		size = 1;
	}

	public Queue() {
		linkedList = new LinkedList();
		size = 0;
	}

	public void enQueue(Object o) {
		linkedList.add(o);
		size++;
	}

	private void checkCapacity(){
		if(size<=0){
			throw new IndexOutOfBoundsException();
		}
	}
	public Object deQueue() {
		checkCapacity();
		Object o = linkedList.removeFirst();
		size--;
		return o;
	}

	public boolean isEmpty() {
		if(size==0){
			return true;
		}
		return false;
	}

	public int size() {
		return size;
	}
}
