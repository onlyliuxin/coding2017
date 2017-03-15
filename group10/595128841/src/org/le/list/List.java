package org.le;

public interface List<E> {
	
	void add(E obj);
	
	void add(int index,E obj);
	
	void remove(E obj);
	
	E remove(int index);
	
	E get(int index);
	
	E set(int index,E obj);
	
	int indexOf(E obj);

}
