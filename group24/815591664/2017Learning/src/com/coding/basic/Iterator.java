package com.coding.basic;
 
public interface Iterator {
	//接口里的成员变量默认都是final static的
//	int cursor = 0;
	public boolean hasNext();
	public Object next() throws Exception;

}
