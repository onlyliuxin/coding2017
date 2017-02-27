package basic;

public class Queue {
	private LinkedList elementData = new LinkedList();
	
	public void enQueue(Object o){		
		elementData.addLast(o);
	}
	
	
	public Object deQueue(){
		Object tempObj =elementData.removeFirst();
		return tempObj;
	}
	
	public boolean isEmpty(){
		if (elementData.size()==0){

			return true;
		}
		else {
			return false;
		}
	}
	
	public int size(){
		return elementData.size();
	}
}
