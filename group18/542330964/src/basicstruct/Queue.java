package basicstruct;

public class Queue {
	
	private LinkedList queue = new LinkedList();
	//进队列
	public void enQueue(Object o) {
		queue.addLast(o);
	}
	//出队列
	public Object deQueue() {
		return queue.removeFirst();
	}
	
	public int size() {
		return queue.size();
	}

	public boolean isEmpty() {
		return queue.isEmpty();
	}
}