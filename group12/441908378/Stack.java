
public class Stack {
	private ArrayList elementData = new ArrayList();

	private int size = 0;

	public void push(Object o){		
	elementData.add(o);
	}
	
	public Object pop(){
		Object o=elementData.get(elementData.size());
		elementData.remove(elementData.size());
		return o;
	}
	
	public Object peek(){
		Object o=elementData.get(elementData.size());
		return o;
	}
	public boolean isEmpty(){
		if(elementData.size()==0){
			return true;
		}
		return false;
	}
	public int size(){
		return elementData.size();
	}
}
