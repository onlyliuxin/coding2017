package com.github.wdn.coding2017.basic.lru;

import com.github.wdn.coding2017.basic.LinkedList;

/**
 * 用双向链表实现LRU算法
 * @author liuxin
 *
 */
public class LRUPageFrame {
	private int capacity;
	private LinkedList cache = new LinkedList();
	public LRUPageFrame(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * 获取缓存中对象
	 * 
	 * @param pageNum
	 * @return
	 */
	public void access(int pageNum) {
		if(capacity==cache.size()){
			int exist = exist(pageNum);
			if(exist>-1){
				cache.addFirst(cache.remove(exist));
			}else{
				cache.removeLast();
				cache.addFirst(pageNum);
			}
		}else {
			cache.addFirst(pageNum);
		}
	
	}
	private int exist(int pageNum){
		for (int i = 0; i < cache.size(); i++) {
			if(cache.get(i).equals(pageNum)){
				return i;
			}
		}
		return -1;
	}
	public String toString(){
		return cache.toString().replace("[","").replace("]","");
	}
	
}
