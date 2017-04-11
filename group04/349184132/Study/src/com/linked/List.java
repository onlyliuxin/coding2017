package com.linked;


public interface List<T> {
	public boolean add(T o);

	public boolean add(int index, T o);

	public T get(int index);
	
	T set(int index, T element);

	public T remove(int index);

	public T remove(T o);

	public int size();

	public boolean isEmpty();
	
	public Iterator<T> iterator();
	
	public boolean contains(Object o);
	
	int indexOf(Object o);
	
	
	Object[] toArray();
	
	void clear();
	
}
