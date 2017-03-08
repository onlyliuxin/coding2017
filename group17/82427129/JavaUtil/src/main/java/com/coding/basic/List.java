package com.coding.basic;

/**
 * 代码进行了多次重构，按照JDK源码的思路进行编写，<br/>
 * <br/>
 * 重点1：rangeCheck和elementIndexCheck 完全管理了所有的Index的检测工作，这一点就 省去了其他地方关于下标是否越界的检测<br/>
 * <br/>
 * 重点2：arrayList的elementData(int i)以及LinkedList的node(int i)方法通过重构统一了通过下标获取元素的操作<br/>
 * 
 * @author Walker
 *
 * @param <E>
 */
public interface List<E> {
	
	void add(E o);
	
	void add(int index, E o);
	
	Object get(int index);
	
	Object remove(int index);
	
	int size();
}
