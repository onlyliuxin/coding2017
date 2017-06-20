package com.coding.basic;

import com.coding.basic.array.ArrayList;

public class Test {
	public static void main(String[] args) {
		List list = new ArrayList();
		
		for(int i=0;i<20;i++){
			String data = "测试" + i;
			list.add(i,data);
		}
		System.out.println(list.size());
		System.out.println(list.get(19));
	}
}
