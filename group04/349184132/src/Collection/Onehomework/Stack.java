package Collection.Onehomework;

public class Stack {
	private ArrayList elementData = new ArrayList();
	private int size = 0;
	public void push(Object o){
		elementData.add(o);
		size++;
	}

	
	public Object pop(){
		if(isEmpty()){
			try {
				throw new IllegalAccessException();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return elementData.get(--size);
	}
	
	public Object peek(){
		return elementData.get(size-1);
	}

	public boolean isEmpty(){
		return size==0;
	}

	public int size(){
		return size;
	}
}
