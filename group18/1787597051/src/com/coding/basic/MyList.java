package com.coding.basic;

public interface MyList {
	public abstract void add(Object o);
	public abstract void add(int index, Object o);
	public abstract Object get(int index);
	public abstract Object remove(int index);
	public abstract int size();
}
