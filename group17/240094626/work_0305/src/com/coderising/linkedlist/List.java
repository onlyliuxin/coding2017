package com.coderising.linkedlist;

public interface List<E> {
	public void add(E e);
	public void add(int index, E e);
	public E get(int index);
	public E remove(int index);
	public int size();
}
