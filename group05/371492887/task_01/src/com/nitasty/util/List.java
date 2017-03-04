package com.nitasty.util;


public interface List {

	int size();

	boolean isEmpty();

	boolean contains(Object o);

	boolean add(Object o);

	boolean add(int index, Object o);

	boolean addAll(Object[] o);

	boolean addAll(int index, Object[] o);

	Object remove(int index);
	
	boolean remove(Object o);

	boolean removeAll(List list);

	Object get(int index);
	
	Object set(int index, Object o);
	
	int indexOf(Object o);
	
	int lastIndexOf(Object o);
	
	Iterator iterator();
	
	Object[] toArray();
	
	void clear();
	
	// boolean containsAll();

	// boolean retainAll(Object[] o);



}
