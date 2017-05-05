package com.xusheng.linkedlist;

import java.util.Iterator;

public class TestMain {

	public static void main(String[] args) {
		MyLinkedList<String> mll = new MyLinkedList<String>();
		mll.add(0, "haha1");
		mll.add(1,"haha2");
		mll.add(2, "haha3");
		mll.add(3,"haha4");
		mll.add("haha5");
		for(int i=0;i<mll.size();i++){
			System.out.println(mll.get(i));
		}
		System.out.println(mll.isEmpty());
		System.out.println(mll.size());
		System.out.println(mll.set(3, "heihei"));
		System.out.println("get:"+mll.get(4));
		System.out.println("remove:"+mll.remove(3));
		for(int i=0;i<mll.size();i++){
			System.out.println(mll.get(i));
		}
		
//		while(i.hasNext()){
//			System.out.println("迭代器2："+i.next());
//			i.remove();
//		}
		
		System.out.println(mll.contains("haha0"));
		mll.change(0);
		Iterator i = mll.iterator();
		while(i.hasNext()){
			System.out.println("迭代器1："+i.next());
		}
	}
}
