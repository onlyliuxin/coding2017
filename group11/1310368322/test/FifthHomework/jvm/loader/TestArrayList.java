package com.coderising.jvm.loader;

import java.util.ArrayList;

public class TestArrayList {
	public static void main(String[] args) {
		ArrayList list = new ArrayList();
		for(int i = 0; i < 5; i++){
			list.add(i);
		}
		System.out.println(list.size());
	}

}
