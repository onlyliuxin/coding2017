

public class Stack {
	private ArrayList elementData = new ArrayList();
	
	public void push(Object o){		

		elementData.add(o);
	}
	
	public Object peek(){
		return elementData.get(0);
	}
	
	public Object pop(){
		Object a = null;
		if(elementData.size() > 0) {
			
			a = elementData.get(elementData.size() - 1);
			elementData.remove(elementData.size() - 1);
		}
		return a;
	}
	
	public boolean isEmpty(){
		return elementData.size() == 0;
	}
	
	public int size(){
		return elementData.size();
	}
	
}
