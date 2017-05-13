package main;

public interface List {
	public void add(Object o);
	public void add(int index,Object o);
	public Object remove(int index);
	public Object get(int index);
	public int size();
}
