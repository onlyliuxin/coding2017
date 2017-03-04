package com;

public interface MyList {

	public boolean add(Object o);

	public void add(int index, Object o);

	public Object get(int index);

	public int size();

	public Object remove(int index);
}
