package com.coderising.jvm.GC;

import java.util.ArrayList;
import java.util.List;

public class OutOfMemory_PermGen_space {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<>();
		int i = 1;
		try {
			while (true) {
				list.add(String.valueOf(i++).intern());
				System.out.println("i: "+i);
			}
		} finally {
			System.out.println("运行次数：" + i);
		}
	}
}
