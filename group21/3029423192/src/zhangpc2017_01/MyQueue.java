
public class MyQueue {
	
	private MyLinkedList elementData = new MyLinkedList();
	private int size = 0;
	
	public void enQueue(Object o){
		elementData.add(o);
		size++;
	}
	
	public Object deQueue(){
		size--;
		return elementData.head.data;
	}
	
	public boolean isEmpty(){
		boolean flag;
		if(size == 0){
			flag = false;
		}
		flag = true;
		return flag;
	}
	
	public int size(){
		return size;
	}
}
