package com.coding.basic;


public interface List<E> {
	public void add(E e);
	public void add(int index, E e);
	//public E get(int index);
	public void remove(int index);
	public boolean contains(E e);
	public int size();
	public boolean isEmpty();
	public void clear();
}
