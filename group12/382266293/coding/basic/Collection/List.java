package Collection;

public interface List<E> {
	
	public void add(E e);
		
	public int size();
	
	public E get(int index);
	
	public boolean isEmpty();
	
	public int indexOf(E e);

	
}
