package com.github.qq809203042.coding2017.basic.structures;
/*
 * 自定义list接口
 */
public interface MyList {
//	查
	public abstract Object get(int index); 
	
//	增
	public abstract boolean add(Object obj);
//	在指定位置增
	public abstract boolean add(Object obj,int index);
	
//	删除
	public abstract Object remove(int index);
	
//	判断大小
	public abstract int size();
//	判断是否为空
	public abstract boolean isEmpty();
	

}
