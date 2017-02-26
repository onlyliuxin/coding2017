package data_structure;

public class MyQueue {
	private  MyLinkedList elementData =new MyLinkedList();
	public void enQueue(Object o){	
		elementData.add(o);
	}
	
	public Object deQueue(){
		return elementData.removeFirst();
	}
	
	public boolean isEmpty(){
		return elementData.size()==0;
	}
	
	public int size(){
		return elementData.size();
	}

}
