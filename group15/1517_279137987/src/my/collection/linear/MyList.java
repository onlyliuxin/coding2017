package my.collection.linear;

public interface MyList {
	
	public void add(Object obj);					//add to end
	
	public void add(int index, Object obj);			//add to specific position
	
	public Object get(int index);					//return specific element
	
	public Object remove(int index);				//return deleted specific element
	
	public int size();								//return the size of ArrayList
}
