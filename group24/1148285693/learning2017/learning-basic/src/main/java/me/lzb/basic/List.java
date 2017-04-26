package me.lzb.basic;

/**
 * list接口
 * Created by LZB on 2017/3/11.
 */
public interface List {
	void add(Object o);
	void add(int index, Object o);
	Object get(int index);
	Object remove(int index);
	int size();
}
