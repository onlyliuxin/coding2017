package com.ikook.basic_data_structure;

/**
 * @author ikook   QQ号码: 935542673
 */
public interface MyList {
	
	public void add(Object o);
	public void add(int index, Object o);
	
	public int size();
	
	public boolean isEmpty();
	
	public Object get(int index);
	
	public Object remove(int index);
	public boolean remove(Object obj);
	
	public Object set(int index, Object obj);
	
}
