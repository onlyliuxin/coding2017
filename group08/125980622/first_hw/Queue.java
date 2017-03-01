
public class Queue {

    LinkedList queueList = new LinkedList();

	public void enQueue(Object o){
        queueList.addLast(o);
	}
	
	public Object deQueue(){
		return queueList.removeFirst();
	}
	
	public boolean isEmpty(){
        return queueList.size() <= 0;
	}
	
	public int size(){
        return queueList.size();
	}
}
