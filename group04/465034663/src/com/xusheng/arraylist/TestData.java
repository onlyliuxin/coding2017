package com.xusheng.arraylist;

import java.util.Iterator;

public class TestData {

	public static void main(String[] args) {
		MyArrayList<String> mal = new MyArrayList<String>();
		mal.add("haha");
		mal.add("haha2");
//		System.out.println(mal.size());
//		for(int i=0;i<mal.size();i++){
//			System.out.println(mal.get(i));
//		}
//		System.out.println(mal.set(1, "heihei"));
//		System.out.println(mal.get(1));
//		System.out.println(mal.size());
//		System.out.println(mal.remove(1));
//		for(int i=0;i<mal.size();i++){
//			System.out.println(mal.get(i));
//		}
//		System.out.println(mal.size());
		Iterator i = (Iterator) mal.iterator();
		while(i.hasNext()){
			System.out.println(i.next());
			
		}
	}
}
