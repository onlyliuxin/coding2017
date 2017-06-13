package com.zhangqian.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class Test {
	public static void main(String[] args) {
		test("test");
		
	}
	public static void test(String a){
		ArrayList<String> list = new ArrayList<String>();
		HashSet<String> set = new HashSet<String>();
		set.add("1");
		list.add(a);
		Iterator<String> iterator = list.iterator();
	}
}
