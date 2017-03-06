import java.util.EmptyStackException;

public class Stack {
	private ArrayList elementData = new ArrayList();
	
	public void push(Object o){
		elementData.add(o);
	}
	
	public Object pop(){
		if(!isEmpty())
			return elementData.remove();
		else 
			throw new EmptyStackException();
	}
	public Object peek(){
		int len = elementData.size();
		if(!isEmpty())
			return elementData.get(len-1);
		throw new EmptyStackException();
	}
	public boolean isEmpty(){
		if(elementData.size()==0)
			return true;
		else
			return false;
	}
	public int size(){
		return elementData.size();
	}
/*	public static void main(String[] args){
		Stack s = new Stack();
		s.push("123");
		s.push("1");
		s.push("1234");
		s.push("1253");
		s.push("1263");
		System.out.println(s.size());
		System.out.println(s.peek());
		System.out.println(s.pop());
		System.out.println(s.isEmpty());
	}*/
}

