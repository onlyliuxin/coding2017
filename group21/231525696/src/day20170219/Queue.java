package day20170219;

/**
 * 队列，先进先出
 * @author RedKnife
 *
 */
public class Queue {
	
	private LinkedList elementData;
	
	public void enQueue(Object o){
		elementData.add(o);
	}
	
	public Object deQueue(){
		return elementData.removeLast();
	}
	
	public boolean isEmpty(){
		return elementData.size() == 0;
	}
	
	public int size(){
		return elementData.size();
	}
	
}
