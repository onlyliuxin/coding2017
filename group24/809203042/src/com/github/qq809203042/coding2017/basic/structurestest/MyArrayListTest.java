package com.github.qq809203042.coding2017.basic.structurestest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.github.qq809203042.coding2017.basic.structures.MyArrayList;

 /*
  *  用于测试MyArrayList
  */
 
public class MyArrayListTest {

	public static void main(String[] args) {
		
		
		MyArrayList mList = new MyArrayList();
		mList.add(new String("hahah"));
		mList.add(new String("heihei"));
		mList.add(new String("xixi"));
		mList.add(new String("papapa"));
		mList.add(new String("xiaoqiang"));
		mList.add(new String("xiaoming"));
		
		System.out.println(mList);
		System.out.println(mList.get(0));
		System.out.println(mList);
		System.out.println(mList.add(new String("新元素"),3));
		System.out.println(mList);
		System.out.println(mList.remove(0));
		System.out.println(mList);
		System.out.println(mList.isEmpty());
		System.out.println(mList);
		
		System.out.println(mList.size());
	}

}
