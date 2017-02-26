import java.util.EmptyStackException;

public class Queue {
	private ArrayList elementData = new ArrayList();
	public void enQueue(Object o){	
		elementData.add(o);
	}
	
	public Object deQueue(){
		if(!isEmpty())
			return elementData.remove(1);
		else 
			throw new EmptyStackException();
	}

	
	public boolean isEmpty(){
		if(elementData.size()==0)
			return true;
		return false;
	}
	
	public int size(){
		return elementData.size();
	}
	public static void main(String[] args){
		Queue q = new Queue();
		q.enQueue("123");
		q.enQueue("a");
		q.enQueue("f");
		q.enQueue("t");
		q.enQueue("w");
		
		System.out.println(q.deQueue());
		System.out.println(q.size());
		System.out.println(q.isEmpty());
		
}
}
