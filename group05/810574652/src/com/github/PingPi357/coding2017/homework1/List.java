package com.github.PingPi357.coding2017.basic;

public interface List {
	public void add(Object o);

	public void add(int index, Object o); // Object 首字母大写,不然会出现"object" can not
											// be resolved a type错误

	public Object remove(int index);

	public Object get(int index);

	public int size(); // 获取List的实际大小,而length定义的是总的容量
	/*
	 * public void change(int index, Object o); 实现改变特定index处的值
	 */
}