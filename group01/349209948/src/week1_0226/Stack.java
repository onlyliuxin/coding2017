package week1_0226;
import java.util.EmptyStackException;
public class Stack {
	private ArrayList elementData = new ArrayList();
	
	public void push(Object o){	
		elementData.add(o);
	}
	
	public Object pop(){
		checkStack();
		return elementData.remove(elementData.size() - 1);
	}
	private void checkStack(){
		if (elementData.size() == 0){
			throw new EmptyStackException();
		}
	}
	public Object peek(){
		checkStack();
		return elementData.get(elementData.size() - 1);
	}
	public boolean isEmpty(){
		return elementData.size() == 0;
	}
	public int size(){
		return elementData.size();
	}
}
