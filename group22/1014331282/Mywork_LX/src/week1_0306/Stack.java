package week1_0306;
/**
 * ջ������ȳ� LIFO
 * 
 * */
public class Stack {

	private LinkedList storage = new LinkedList();

	public void push(Object o)
	{
		storage.addFirst(o);
	}
	
	public Object pop()
	{
		return storage.removeFirst();
	}
	
	public Object peek()
	{
		return storage.get(0);
	}
	
	public boolean isEmpty()
	{
		if(storage.get(0)==null)
			return true;
		else return false;
	}
	
	public int size()
	{
		return storage.size();
	}
}
