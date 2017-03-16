package my.collection.linear;

public class MyStack {
	private int capacity = 10;
	
	private MyArrayList elementData = new MyArrayList(capacity);
	
	public void push(Object obj){
		elementData.add(obj);
	}
	
	public Object pop(){
		int tmpSize = elementData.size()-1;
		Object tmpElement = elementData.get(tmpSize);
		elementData.remove(tmpSize);
		return tmpElement;
	}
	
	public Object peek(){
		return elementData.get(elementData.size()-1);
	}
	
	public boolean isEmpty(){
		boolean isEmp;
		if(elementData.size() == 0){
			isEmp = true;
		}else{
			isEmp = false;
		}
		return isEmp;
	}
	
	public int size(){
		return elementData.size();
	}
}
