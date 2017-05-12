package com.sprint.basic.list;
import com.sprint.basic.Iterator;
public interface List {
	public boolean add(Object o);
	public boolean add(int index, Object o);
	public Object get(int index);
	public Object remove(int index);
	public int size();
	public Iterator iterator();
}
