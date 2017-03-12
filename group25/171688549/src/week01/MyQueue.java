package week01;

public class MyQueue  implements List{
	 Object elementData;
	public void enQueue(Object o){		
		
		((MyQueue) elementData).add(0, o);
	}
	
	public Object deQueue(){
		return ((MyQueue) elementData).remove(((MyQueue) elementData).size() - 1);
	}
	
	public boolean isEmpty(){
		return ((MyQueue) elementData).isEmpty();
	}
	
	public int size(){
		return ((MyQueue) elementData).size();
	}

	@Override
	public void add(Object o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(int index, Object o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}
}
