package com.github.qq809203042.coding2017.basic.structurestest;

import com.github.qq809203042.coding2017.basic.structures.MyLinkedList;

/*
 * 用于测试MyLinkedList类
 */
public class MyLinkedListTest {

	public static void main(String[] args) {
		MyLinkedList mList = new MyLinkedList();
		System.out.println(mList.add(new String("hahah")));
		System.out.println(mList.add(new String("heihei")));
		System.out.println(mList.add(new String("xixi")));
		System.out.println(mList.add(new String("papapa")));
		System.out.println(mList.add(new String("xiaoqiang")));
		System.out.println(mList.add(new String("xiaoming")));
		
		System.out.println(mList.size());
		System.out.println(mList);
		System.out.println(mList.get(0));
		System.out.println(mList);
		System.out.println(mList.add(new String("新元素"),0));
		mList.addFirst(new String("新元素2"));
		mList.addLast(new String("新元素3"));
		
		System.out.println(mList.size());
		System.out.println(mList);
		System.out.println(mList.remove(5));
		System.out.println(mList);
	
		System.out.println(mList.size());
	}

}
