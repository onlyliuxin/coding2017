package data2_19;
public class Queue {
	
	private LinkedList linkedList = new LinkedList();
	private int elementCount;
	
	public Queue() {
		this.elementCount = 0;
	}
	
	public void enQueue(Object o){
		linkedList.addLast(o);
		elementCount++;
	}
	
	public Object deQueue(){
		Object object = linkedList.removeFirst();
		elementCount--;
		return object;
	}
	
	public boolean isEmpty(){
		return elementCount == 0;
	}
	
	public int size(){
		return elementCount;
	}
	
	public static void main(String[] args) {
		Queue queue = new Queue();
		queue.enQueue(2);
		queue.enQueue(3);
		System.out.println(queue.isEmpty());
		System.out.println(queue.size());
		System.out.println(queue.deQueue());
	}
}