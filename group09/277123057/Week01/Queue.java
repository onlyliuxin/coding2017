package Week01;
/*
 * time:2017-2-25 13:46 created by Doen
 * 
 * */
public class Queue {
	private LinkedList elementData = new LinkedList();
	//进队列
	
	public void enQueue(Object o){
		elementData.add(o);
	}
	
	//出队列
	public Object deQueue(){
		if (isEmpty())
			throw new UnsupportedOperationException();
		return elementData.remove(0);
	}
	
	public boolean isEmpty(){
		return elementData.size() == 0;
	}
	
	public int size(){
		return elementData.size();
	}
	
}
