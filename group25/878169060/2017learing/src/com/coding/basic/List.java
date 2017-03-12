package com.coding.basic;

/**
 * 
 * @ClassName:     List 
 * @Description:list接口  提供增删改查
 * @author:    QQ：878169060
 * @date:        2017年3月11日 上午12:19:56
 */
public interface List {
	public void add(Object o);

	public void add(int index, Object o);

	public Object get(int index);

	public Object remove(int index);

	public int size();
}
